package com.medical.dto;

import com.medical.constants.IsHotProductEnum;
import com.medical.constants.StatusCodeProductEnum;
import com.medical.entity.Brand;
import com.medical.entity.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Integer id;
    private String title;

    private String descriptions;

    private Integer originalPrice;

    private Integer promotionPrice;

    private Integer amount;
    private Integer currentAmount;
    private IsHotProductEnum isHot;
    private StatusCodeProductEnum status;
    private Brand brand;
    private CategoryDTO category;
    private Ticket ticket;

    private List<ProductImagesDTO> productImages;

    private List<RatingDTO> productRatesList;


    @Data
    @NoArgsConstructor
    public static class RatingDTO extends  RepresentationModel<RatingDTO> {
        private Integer id;
        private String comment;
        private Date createdAt;
        private Date updateAt;
        private CustomerDTO user;
    }

    @Data
    @NoArgsConstructor
    public static class CategoryDTO extends RepresentationModel<CategoryDTO> {
        private Integer id;
        private String name;
    }
    @Data
    @NoArgsConstructor
    static class ProductImagesDTO{
        private String imageUrl;
    }
}

