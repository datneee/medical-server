package com.medical.repositories;

import com.medical.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartItemRepository extends JpaRepository<CartItem , Integer> {

    CartItem findCartItemById(Integer id);

    void deleteById(Integer id);

    void deleteByIdIn(List<Integer> ids);
}
