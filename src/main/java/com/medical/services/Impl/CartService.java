package com.medical.services.Impl;

import com.medical.dto.ProductDTO;
import com.medical.entity.*;
import com.medical.filters.AddCartParams;
import com.medical.repositories.ICartRepository;
import com.medical.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository repository;

    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IOrderItemService orderItemService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Cart getCartByUserId(Integer id) {

        return repository.findCartByUserId(id);
    }

    @Override
    public void addCartItemToCart(AddCartParams params) throws Exception {
        System.out.println(params);
        Cart cart = repository.findCartByUserId(params.getUserId());
        ProductDTO productDTO = productService.getProductById(params.getProductId());
        if(productDTO.getAmount() == 0)
            throw new Exception("Sản phẩm đã hết hàng");
        Product product = modelMapper.map(productDTO, Product.class);
        CartItem cartItem = null;
        for (CartItem item:cart.getCartItemList()) {
            if(item.getProduct().getId() == product.getId()){
                cartItem = item;
                break;
            }
        }
        if(cartItem != null){
            cartItem.setAmount(cartItem.getAmount() + params.getAmount());
            cartItemService.updateCartItemAmount(cartItem.getId(), cartItem);
        }
        else{
            cartItemService.createCartItem(new CartItem(params.getAmount() , cart , product));
            updateCartAmount(cart.getAmount() + 1 , cart);
        }
    }



    @Override
    public void buyListCartItems(Integer userId, String shipment, String shipAddress) {
        Cart cart = this.getCartByUserId(userId);
        Order order = new Order(userService.findById(userId), 0);
        order.setShipment(shipment);
        order.setShipAddress(shipAddress);
        orderService.createOrder(order);
        List<Integer> listId = new ArrayList<>();
        for (CartItem item: cart.getCartItemList()) {
            productService.updateProductAmount(item.getProduct() , item.getProduct().getCurrentAmount() - item.getAmount());
            OrderItem orderItem = new OrderItem(item.getAmount() , order , item.getProduct());
            orderItemService.createOrderItems(orderItem);
            listId.add(item.getId());
        }
        cartItemService.deleteByIdIn(listId , userId);
    }

    @Override
    public Cart updateCartAmount(Integer amount ,  Cart cart) {
        cart.setAmount(amount);
        repository.save(cart);
        return cart;
    }


}
