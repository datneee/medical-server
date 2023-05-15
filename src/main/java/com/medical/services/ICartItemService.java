package com.medical.services;

import com.medical.entity.CartItem;

import java.util.List;

public interface ICartItemService {

    CartItem getCartItemById(Integer id);

    CartItem createCartItem(CartItem cartItem);

    CartItem updateCartItemAmount(Integer id,CartItem cartItem);

    void updateCartItemAmount(Integer id,Integer Amount);

    void deleteById(Integer id , Integer userId);

    void deleteByIdIn(List<Integer> ids , Integer userId);


}
