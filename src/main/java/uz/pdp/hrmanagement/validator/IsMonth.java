package uz.pdp.hrmanagement.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.pdp.hrmanagement.validator.impl.IsMonthValidator;

import java.lang.annotation.*;;

@Documented
@Constraint(validatedBy = IsMonthValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsMonth {
    String message() default "month must be between 1 and 12";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
