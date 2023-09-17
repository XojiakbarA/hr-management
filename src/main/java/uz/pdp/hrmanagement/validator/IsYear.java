package uz.pdp.hrmanagement.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.pdp.hrmanagement.validator.impl.IsYearValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsYearValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsYear {
    String message() default "year must be between 2000 and 2200";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
