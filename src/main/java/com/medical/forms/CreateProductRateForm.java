package com.medical.forms;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateProductRateForm {
    private Integer userId;

    private Integer productId;

    private Integer value;

    private String comment;
}
