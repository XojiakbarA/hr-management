package uz.pdp.hrmanagement.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import uz.pdp.hrmanagement.validator.impl.IsFutureValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsFutureValidator.class)
@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsFuture {
    String message() default "date must be future";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
