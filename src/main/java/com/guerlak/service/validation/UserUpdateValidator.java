package com.guerlak.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.guerlak.model.User;
import com.guerlak.model.dto.UserDTO;
import com.guerlak.repositories.UserRepo;

public class UserUpdateValidator implements ConstraintValidator<UserUpdate, UserDTO> {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	HttpServletRequest req;

	@Override
	public void initialize(UserUpdate ann) {
	}

	@Override
	public boolean isValid(UserDTO objDto, ConstraintValidatorContext context) {

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) req.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		List<FieldMessage> list = new ArrayList<>();
		
		Integer uId = Integer.parseInt(map.get("id"));

		User checkUserExist = userRepo.findByEmail(objDto.getEmail());

		if (checkUserExist.getId().equals(uId) && checkUserExist != null) {
			list.add(new FieldMessage("email", "this email already exists"));
		}

		// adding custom erros to the framework
		// this allows to get these erros in ResourcesExceptionsHandler
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}

	class FieldMessage {
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
