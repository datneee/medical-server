package com.medical.repositories;

import com.medical.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICartRepository extends JpaRepository<Cart, Integer> {

    Cart findCartByUserId(Integer id);



}
