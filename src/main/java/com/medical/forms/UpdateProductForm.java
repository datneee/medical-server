package com.medical.forms;

import com.medical.entity.Product;
import com.medical.validations.ProductTitleNotExists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UpdateProductForm {

    @NotBlank
    @Length(min = 3 , message = "Tên sản phảm phải dài hơn 3 kí tự")
    @ProductTitleNotExists(message = "Tên sản phẩm không được trùng")
    private String title;

    @NotBlank
    private String descriptions;

    @Min(value = 0 , message = "Giá gốc phải hơn  0")
    private int originalPrice;

    @Min(value = 0 , message = "Giá gốc phải hơn  0")
    private int promotionPrice;

    @Min(value = 0 , message = "Số lượng tối thiểu  0")
    private Integer currentAmount;
    @Min(value = 1 , message = "Số lượng tối thiểu  1")
    private Integer amount;
    private String isHot;
    private String status;
    private Integer ticketId;


//    @NotNull
//    private Integer categoryId;
//
//    @NotNull
//    private Integer brandId;

//    private List<CreateProductImageForm> productImages;

    public Product toEntity(){
        Product p = new Product(
                title,
                descriptions,
                originalPrice,
                promotionPrice,
                currentAmount,
                amount);
        return p;
    }


}
