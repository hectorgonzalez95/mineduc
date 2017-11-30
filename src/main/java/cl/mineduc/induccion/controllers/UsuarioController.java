package cl.mineduc.induccion.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.mineduc.induccion.modelo.Curso;
import cl.mineduc.induccion.modelo.Otro;
import cl.mineduc.induccion.services.InduccionService;


@Controller
@RequestMapping("usuario")
public class UsuarioController {

	
	@Autowired
	private InduccionService induccionServicio;	
	
	@Autowired
	private ObjectMapper om;
	
	private static Logger logger = LogManager.getLogger(UsuarioController.class);

	@RequestMapping(value = "form", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView form(@RequestParam(required = false) Integer error) {
		
		ModelAndView mv = new ModelAndView();		
		mv.addObject("jsonRegiones", induccionServicio.obtenerJsonRegiones());	
		return mv;
	}	
	
	

	@RequestMapping(value = "ajaxform", method = { RequestMethod.GET })
	public ModelAndView ajaxform() {
		
		List<Curso> cursos = induccionServicio.obtenerCursos();
		ModelAndView mv = new ModelAndView();		
		mv.addObject("cursos", cursos);	
		return mv;
	}	
	
	@RequestMapping(value = "registrarCursoJSON", method = {RequestMethod.POST},produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String registrarCursoJSON(Integer id,String curso){
		
		String json="";			
	
		induccionServicio.insertarCursoAjax(id,curso);		
		List<Curso> cursos = induccionServicio.obtenerCursos();
		try {
			json =  om.writeValueAsString(cursos);
		}catch (IOException e) {
			logger.error("Error al convertir cursos a json ",e);
		}
		
		return json;
	}

}
