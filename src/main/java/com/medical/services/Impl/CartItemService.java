package com.medical.services.Impl;

import com.medical.entity.Cart;
import com.medical.entity.CartItem;
import com.medical.repositories.ICartItemRepository;
import com.medical.services.ICartItemService;
import com.medical.services.ICartService;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartItemService implements ICartItemService {

    @Autowired
    private ICartItemRepository repository;

    @Autowired
    private ICartService cartService;


    @Override
    @NotFound(action = NotFoundAction.EXCEPTION)
    public CartItem getCartItemById(Integer id) {
        return repository.findCartItemById(id);
    }

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        repository.save(cartItem);
        return cartItem;
    }

    @Override
    public CartItem updateCartItemAmount(Integer id ,CartItem cartItem) {
        cartItem.setId(id);
        return repository.save(cartItem);
    }

    @Override
    public void updateCartItemAmount(Integer id, Integer Amount) {
        CartItem cartItem = repository.findCartItemById(id);
        cartItem.setAmount(Amount);
        repository.save(cartItem);
    }

    @Override
    @Transactional
    public void deleteById(Integer id , Integer userId) {
        Cart cart = cartService.getCartByUserId(userId);
        repository.deleteById(id);
        cartService.updateCartAmount(cart.getAmount() - 1 , cart);
    }

    @Override
    @Transactional
    public void deleteByIdIn(List<Integer> ids , Integer userId) {
        Cart cart = cartService.getCartByUserId(userId);
        repository.deleteByIdIn(ids);
        cartService.updateCartAmount(cart.getAmount() - ids.size() , cart);
    }
}
