package uz.pdp.hrmanagement.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.pdp.hrmanagement.validator.impl.IsFutureValidator;
import uz.pdp.hrmanagement.validator.impl.IsValidEnumValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsValidEnumValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidEnum {
    Class<? extends Enum<?>> enumClass();
    String message() default "must be any of enum {enumClass}";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
