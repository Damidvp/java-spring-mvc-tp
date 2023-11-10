package fr.diginamic.springmvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<NameFormat, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		return !value.isBlank() && !value.isEmpty() && Character.isUpperCase(value.charAt(0));
	}

}
