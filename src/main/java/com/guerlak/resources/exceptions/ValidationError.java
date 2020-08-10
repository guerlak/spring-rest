package com.guerlak.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;
	@JsonProperty("errors")	
	private List<FieldMessage> list = new ArrayList<>();

	public ValidationError(Instant instant, Integer status, String error, String message, String path) {
		super(instant, status, error, message, path);
	}
	
	public List<FieldMessage> getList(){
		return list;
	}
	
	public void addError(String fieldName, String message) {
		list.add(new FieldMessage(fieldName, message));
	}
	
	class FieldMessage{
		private String fieldName;
		private String message;
		public FieldMessage(String fieldName, String message) {
			super();
			this.fieldName = fieldName;
			this.message = message;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
	
	
}
