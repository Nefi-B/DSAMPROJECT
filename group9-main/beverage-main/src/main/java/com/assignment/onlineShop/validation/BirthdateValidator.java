package com.assignment.onlineShop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class BirthdateValidator implements ConstraintValidator<ValidBirthdate, LocalDate> {

    private static final LocalDate MIN_DATE = LocalDate.of(1900, 1, 1);

    @Override
    public boolean isValid(LocalDate birthday, ConstraintValidatorContext context) {
        if (birthday == null) {
            return true;
        }
        return birthday.isAfter(MIN_DATE);
    }
}