package com.coderwurst.spring.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.validator.routines.EmailValidator;


public class ValidEmailImpl implements ConstraintValidator <ValidEmail, String> {

	private int min;		// to store min value from ValidEmail
	
	@Override
	public void initialize(ValidEmail constraintAnnotation) {
		min = constraintAnnotation.min();
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		// test 1 - is length sufficient
		if(email.length() < min) {
			return false;
		} 		
		
		// uses Apache commons to determine if email is valid or not
		if(!EmailValidator.getInstance(false).isValid(email)) {
			return false;
		}
		
		// as long as email has passed all above tests, return true
		return true;
	}

}
