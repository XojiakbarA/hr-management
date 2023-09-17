package uz.pdp.hrmanagement.validator.impl;

import java.time.Year;
import java.time.format.DateTimeParseException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.hrmanagement.validator.IsYear;

public class IsYearValidator implements ConstraintValidator<IsYear, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        try {
            Year.of(value);
            return true;
        } catch (DateTimeParseException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
    
}
