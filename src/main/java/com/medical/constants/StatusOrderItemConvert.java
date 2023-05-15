package com.medical.constants;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class StatusOrderItemConvert implements AttributeConverter<StatusOrderItem , String> {


    @Override
    public String convertToDatabaseColumn(StatusOrderItem attribute) {
        if(attribute == null)
            return null;
        return attribute.getValue();
    }

    @Override
    public StatusOrderItem convertToEntityAttribute(String status) {
        return StatusOrderItem.of(status);
    }
}
