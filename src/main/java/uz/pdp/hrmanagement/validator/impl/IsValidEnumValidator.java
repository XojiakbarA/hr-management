package uz.pdp.hrmanagement.validator.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import uz.pdp.hrmanagement.validator.IsValidEnum;

import java.util.List;
import java.util.stream.Stream;

public class IsValidEnumValidator implements ConstraintValidator<IsValidEnum, Enum<?>> {
    private List<String> acceptedValues;

    @Override
    public void initialize(IsValidEnum constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClazz().getEnumConstants()).map(Enum::name).toList();
    }

    @Override
    public boolean isValid(Enum<?> anEnum, ConstraintValidatorContext constraintValidatorContext) {
        if (anEnum == null) return true;
        return acceptedValues.contains(anEnum.name());
    }
}
