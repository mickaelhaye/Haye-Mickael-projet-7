package com.nnk.springboot.configuration;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.LengthRule;
import org.passay.MessageResolver;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.RuleResult;
import org.passay.WhitespaceRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nnk.springboot.annotation.ValidPassword;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.SneakyThrows;

/**
 * this class is used to check password contraints.
 * 
 * @author mickael hayé
 * @version 1.0
 */
public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

	private static Logger logger = LoggerFactory.getLogger(PasswordConstraintValidator.class);

	@Override
	public void initialize(final ValidPassword arg0) {
		logger.debug("initialize");

	}

	/**
	 * The password must be at least 8 characters long, one upper case, one lower
	 * case, one number, one symbol, no spaces.
	 */
	@SneakyThrows
	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {
		logger.debug("isValid");
		// customizing validation messages
		Properties props = new Properties();
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream("passay.properties");
		props.load(inputStream);
		MessageResolver resolver = new PropertiesMessageResolver(props);

		PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(

				// length between 8 and 16 characters
				new LengthRule(8, 999),

				// at least one upper-case character
				new CharacterRule(EnglishCharacterData.UpperCase, 1),

				// at least one lower-case character
				// new CharacterRule(EnglishCharacterData.LowerCase, 1),

				// at least one digit character
				new CharacterRule(EnglishCharacterData.Digit, 1),

				// at least one symbol (special character)
				new CharacterRule(EnglishCharacterData.Special, 1),

				// no whitespace
				new WhitespaceRule()));

		// rejects passwords that contain a sequence of >= 5 characters alphabetical
		// (e.g. abcdef)
		// new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
		// rejects passwords that contain a sequence of >= 5 characters numerical (e.g.
		// 12345)
		// new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false)));

		RuleResult result = validator.validate(new PasswordData(password));

		if (result.isValid()) {
			return true;
		}

		List<String> messages = validator.getMessages(result);
		String messageTemplate = String.join(",", messages);
		context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation()
				.disableDefaultConstraintViolation();
		return false;
	}

}