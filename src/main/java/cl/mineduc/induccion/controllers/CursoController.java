package cl.mineduc.induccion.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.induccion.modelo.Curso;
import cl.mineduc.induccion.modelo.validaciones.ValidacionAlumno;
import cl.mineduc.induccion.modelo.validaciones.ValidacionCurso;
import cl.mineduc.induccion.services.InduccionService;

@Controller
@RequestMapping("curso")
public class CursoController {
	
	ValidacionCurso validacionCurso;
	
	@Autowired
	private InduccionService induccionServicio;	
	
	public CursoController() {
		this.validacionCurso= new ValidacionCurso();
	}

	@RequestMapping(value = "registrar", method = {RequestMethod.GET})
	public ModelAndView registrar(){		
		
		ModelAndView mv = new ModelAndView();		
		return mv;
		
	}
	
	@RequestMapping(value = "registrar", method = {RequestMethod.POST})
	public ModelAndView registrar(@Valid Curso curso,BindingResult result){
		
		this.validacionCurso.validate(curso, result);
		if(result.hasErrors()){
			ModelAndView mv = new ModelAndView();		
			mv.addObject("errores", result.getAllErrors());
			return mv;
			
		}else{
			
			induccionServicio.insertarCurso(curso);
			
			ModelAndView mv = new ModelAndView();		
			mv.setViewName("redirect:/alumno/form");
			
			return mv;
		}
		
				
	}
}
