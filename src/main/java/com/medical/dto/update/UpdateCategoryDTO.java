package com.medical.dto.update;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateCategoryDTO {
    private String name;
    private String descriptions;
    private String status;
}
