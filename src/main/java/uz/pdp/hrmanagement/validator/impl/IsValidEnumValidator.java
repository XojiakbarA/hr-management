package uz.pdp.hrmanagement.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.hrmanagement.validator.IsValidEnum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class IsValidEnumValidator implements ConstraintValidator<IsValidEnum, String> {
    private Class<? extends Enum<?>> enumClazz;

    @Override
    public void initialize(IsValidEnum constraintAnnotation) {
        enumClazz = constraintAnnotation.enumClazz();
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        if (string == null) return true;
        return Arrays.stream(enumClazz.getEnumConstants()).anyMatch(e -> e.name().equalsIgnoreCase(string));
    }
}
