package com.medical.specifications;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FilterSearch {


    private Integer page;

    private Integer size;

    private Integer mnId;

    private Integer mxId;


}
