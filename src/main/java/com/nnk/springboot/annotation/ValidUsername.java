package com.nnk.springboot.annotation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.nnk.springboot.configuration.UsernameConstraintValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * ValidUsername annotation for the entity user column username.
 * 
 * @author mickael hayé
 * @version 1.0
 */
@Documented
@Constraint(validatedBy = UsernameConstraintValidator.class)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface ValidUsername {

	/**
	 * 
	 * @return message
	 */
	String message() default "Invalid Username";

	/**
	 * 
	 * @return Class
	 */
	Class<?>[] groups() default {};

	/**
	 * 
	 * @return Class
	 */
	Class<? extends Payload>[] payload() default {};
}