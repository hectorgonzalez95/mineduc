package cl.mineduc.induccion.modelo.validaciones;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import cl.mineduc.induccion.modelo.Alumno;

public class ValidacionAlumno implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return Alumno.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		Alumno alumno=(Alumno)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required.id","Campo ID es requerido");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required.nombre","Campo Nombre es requerido");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fecha", "required.fecha","Campo Fecha es requerido");
	}

}
