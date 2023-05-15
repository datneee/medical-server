package com.medical.validations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;
import com.medical.validations.ProductTitleNotExists.List;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
        ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { ProductTitleNotExistsValidator.class })
@Repeatable(List.class)
public @interface ProductTitleNotExists {

    String message() default "Product's title must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({  ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
            ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE })
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        ProductTitleNotExists[] value();
    }
}
