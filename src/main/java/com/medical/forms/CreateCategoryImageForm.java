package com.medical.forms;


import com.medical.entity.CategoryImage;
import com.medical.entity.ProductImages;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateCategoryImageForm {
    private String imageUrl;

    public CategoryImage toEntity(){
        return new CategoryImage(imageUrl);
    }
}
