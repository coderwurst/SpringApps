package com.coderwurst.spring.web.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER }) // where validation can be used, remove when not needed
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = com.coderwurst.spring.web.validation.ValidEmailImpl.class)		// class to contain code behind annotation
public @interface ValidEmail {

	String message() default "This does not appear to be a valid email address";		// shown in msg

	Class<?>[] groups() default { };						// always in

	Class<? extends Payload>[] payload() default { };		// always in
	
	// standard Java annotation syntax
	int min() default 5;		// by default the email address must have 5 chars
	
	
}

