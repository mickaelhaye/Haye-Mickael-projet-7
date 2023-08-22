package com.nnk.springboot.configuration;

import org.springframework.beans.factory.annotation.Autowired;

import com.nnk.springboot.annotation.ValidUsername;
import com.nnk.springboot.services.UserService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

public class UsernameConstraintValidator implements ConstraintValidator<ValidUsername, String> {

	@Autowired
	UserService userService;

	@Override
	public void initialize(final ValidUsername arg0) {

	}

	@SneakyThrows
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {

		if (username.equals(userService.getUserNameUpdating())) {
			return true;
		}

		if (!userService.getUserByUsername(username)) {
			return true;
		}

		String messageTemplate = "Username already exist";
		context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}

}