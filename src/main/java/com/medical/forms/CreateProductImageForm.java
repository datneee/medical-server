package com.medical.forms;

import com.medical.entity.ProductImages;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductImageForm {

    private String imageUrl;

    public ProductImages toEntity(){
        return new ProductImages(imageUrl);
    }

}
