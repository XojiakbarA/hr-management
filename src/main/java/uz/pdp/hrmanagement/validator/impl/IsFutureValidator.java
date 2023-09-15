package uz.pdp.hrmanagement.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.hrmanagement.validator.IsFuture;

import java.util.Date;

public class IsFutureValidator implements ConstraintValidator<IsFuture, Date> {
    @Override
    public void initialize(IsFuture constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) {
            return true;
        }
        return date.after(new Date());
    }
}
