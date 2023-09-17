package uz.pdp.hrmanagement.validator.impl;

import java.time.DateTimeException;
import java.time.Month;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.hrmanagement.validator.IsMonth;

public class IsMonthValidator implements ConstraintValidator<IsMonth, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        try {
            Month.of(value);
            return true;
        } catch (DateTimeException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

}
