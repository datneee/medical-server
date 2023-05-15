package com.medical.dto;

import com.medical.entity.Cart;
import com.medical.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Integer id;
    private String username;
    private String email;
    private  String fullName;
    private String password;
    private String phoneNumber;
    private String address;
    private String role;
    private String avatar;
    private CartDTO cart;
    private List<OrderDTO> order;

    @Data
    @NoArgsConstructor
    public static class CartDTO extends RepresentationModel<CartDTO> {
        private Integer id;
        private Integer amount;
    }

    @Data
    @NoArgsConstructor
    public static class OrderDTO extends RepresentationModel<OrderDTO> {
        private Integer id;
        private Integer amount;
    }

}
