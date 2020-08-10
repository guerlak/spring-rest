package com.guerlak.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.guerlak.model.dto.NewUserDTO;
import com.guerlak.service.validation.util.BR;

public class UserInsertValidator implements ConstraintValidator<UserInsert, NewUserDTO> {
	@Override
	public void initialize(UserInsert ann) {
	}

	@Override
	public boolean isValid(NewUserDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		//Validations errors
		if(objDto.getUserType() == null) {
			list.add(new FieldMessage("userType", "Mandatory field: PESSOA_FISICA or PESSOA_JURIDICA"));
		}
		
		if(objDto.getUserType().equals("PESSOA_JURIDICA") && !BR.isValidCNPJ(objDto.getCpfCnpj())) {
			list.add(new FieldMessage("cpfCnpj", "Invalid CNPJ"));
		}
		
		if(objDto.getUserType().equals("PESSOA_FISICA") && !BR.isValidCPF(objDto.getCpfCnpj())) {
			System.out.println("eeeneterrrrr");
			list.add(new FieldMessage("cpfCnpj", "Invalid CPF"));
		}
		
		//adding custom erros to the framework
		//this allows to get these erros in ResourcesExceptionsHandler
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage())
					.addPropertyNode(e.getFieldName())
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
