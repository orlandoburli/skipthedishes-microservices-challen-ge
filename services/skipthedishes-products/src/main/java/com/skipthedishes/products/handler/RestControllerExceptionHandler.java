package com.skipthedishes.products.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.skipthedishes.products.exceptions.ProductException;
import com.skipthedishes.products.exceptions.builder.ValidationErrorDetails;

@ControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@InitBinder
	public void initBinder(final WebDataBinder binder) {
		final StringTrimmerEditor stringtrimmer = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, stringtrimmer);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<?> handleResourceContraintViolation(final ConstraintViolationException validationException) {
		return new ResponseEntity<>(validationException.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ProductException.class)
	public ResponseEntity<?> handleResourceProductException(final ProductException customerException) {

		// @formatter:off
		final ValidationErrorDetails build = ValidationErrorDetails.Builder.newBuilder()
				.timestamp(new Date().getTime())
				.status(HttpStatus.BAD_REQUEST.value())
				.title("Customer Validation Error")
				.detail("Customer Validation Error")
				.developerMessage(customerException.getMessage())
				.build();
		// @formatter:on

		return new ResponseEntity<>(build, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(TransactionSystemException.class)
	public ResponseEntity<?> handleResourceNotFoundException(final TransactionSystemException transactionException) {

		final Throwable cause = transactionException.getRootCause();
		if (cause instanceof ConstraintViolationException) {
			final Set<ConstraintViolation<?>> constraintViolationException = ((ConstraintViolationException) cause)
					.getConstraintViolations();

			final Map<String, Set<String>> validations = this
					.buildMessagesConstraintViolation(constraintViolationException);

			// @formatter:off
			final ValidationErrorDetails build = ValidationErrorDetails.Builder.newBuilder()
					.timestamp(new Date().getTime())
					.status(HttpStatus.BAD_REQUEST.value())
					.title("Field Validation Error")
					.detail("Field Validation Error")
					.developerMessage(((ConstraintViolationException) cause).getClass().getName())
					.validations(validations).build();
			// @formatter:on

			return new ResponseEntity<>(build, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Generic Error", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private final Map<String, Set<String>> buildMessagesConstraintViolation(
			final Set<ConstraintViolation<?>> constraintViolationException) {
		final Map<String, Set<String>> messages = new HashMap<>();

		constraintViolationException.forEach(message -> {
			final String key = ((PathImpl) message.getPropertyPath()).getLeafNode().getName();

			Set<String> messagesConstraints = messages.get(key);
			if (messagesConstraints == null) {
				messagesConstraints = new HashSet<>();
			}
			messagesConstraints.add(message.getMessage());

			messages.put(key, messagesConstraints);
		});

		return messages;
	}
}
