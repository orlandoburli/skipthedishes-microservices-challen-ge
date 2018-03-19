package com.skipthedishes.orders.exceptions.builder;

import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidationErrorDetails extends ErrorDetails {

	private String field;

	private String fieldMessage;

	private Map<String, Set<String>> validations;

	public static final class Builder {
		private String title;
		private int status;
		private String detail;
		private long timestamp;
		private String developerMessage;
		private String field;
		private String fieldMessage;
		private Map<String, Set<String>> validations;

		private Builder() {
		}

		public static Builder newBuilder() {
			return new Builder();
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder status(int status) {
			this.status = status;
			return this;
		}

		public Builder detail(String detail) {
			this.detail = detail;
			return this;
		}

		public Builder timestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public Builder developerMessage(String developerMessage) {
			this.developerMessage = developerMessage;
			return this;
		}

		public Builder field(String field) {
			this.field = field;
			return this;
		}

		public Builder fieldMessage(String fieldMessage) {
			this.fieldMessage = fieldMessage;
			return this;
		}

		public ValidationErrorDetails build() {
			final ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
			validationErrorDetails.setDeveloperMessage(this.developerMessage);
			validationErrorDetails.setTitle(this.title);
			validationErrorDetails.setDetail(this.detail);
			validationErrorDetails.setTimestamp(this.timestamp);
			validationErrorDetails.setStatus(this.status);
			validationErrorDetails.fieldMessage = this.fieldMessage;
			validationErrorDetails.field = this.field;
			validationErrorDetails.setValidations(this.validations);
			return validationErrorDetails;
		}

		public Builder validations(Map<String, Set<String>> validations) {
			this.validations = validations;
			return this;
		}
	}

	public String getField() {
		return this.field;
	}

	public String getFieldMessage() {
		return this.fieldMessage;
	}

	public Map<String, Set<String>> getValidations() {
		return this.validations;
	}

	public void setValidations(Map<String, Set<String>> validations) {
		this.validations = validations;
	}
}