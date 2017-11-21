package cl.mineduc.induccion.modelo.validaciones;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import cl.mineduc.induccion.modelo.Curso;

public class ValidacionCurso {

public void validate(Object target, Errors errors) {
		
		Curso curso=(Curso)target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required.id","Campo ID es requerido");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "required.nombre","Campo Nombre es requerido");
		
		
	}
}
