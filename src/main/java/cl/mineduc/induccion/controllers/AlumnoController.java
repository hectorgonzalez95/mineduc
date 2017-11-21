package cl.mineduc.induccion.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.induccion.modelo.Alumno;
import cl.mineduc.induccion.modelo.validaciones.ValidacionAlumno;
import cl.mineduc.induccion.services.InduccionService;


@Controller
@RequestMapping("alumno")
public class AlumnoController {

	private final Logger log = Logger.getLogger(AlumnoController.class);
	
	@Autowired
	private InduccionService induccionServicio;	
	
	ValidacionAlumno validacionAlumno;
	
	
	
	public AlumnoController() {
		this.validacionAlumno = new ValidacionAlumno();
	}

	@RequestMapping(value = "form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView form(@RequestParam(required = false) Integer error){		
		
		log.info("Ingreso a :" + this.getClass().getName());
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("error",error);	
		List<Alumno> datos = induccionServicio.obtenerAlumnos();
		mv.addObject("datos",datos);		
	
		return mv;
	}
	
	@RequestMapping(value = "edit", method = {RequestMethod.GET})
	public ModelAndView edit(HttpServletRequest request) {		
	
		log.info("Ingreso a :" + this.getClass().getName());
		
		String id =request.getParameter("id");		
		ModelAndView mv = new ModelAndView();	
		mv.addObject("id", id);
		
		return mv;		
		
	}
	
	@RequestMapping(value = "edit", method = {RequestMethod.POST})
	public ModelAndView edit(Alumno alumno) {
		
		log.info("Ingreso a :" + this.getClass().getName());
		
		ModelAndView mv = new ModelAndView();	
		induccionServicio.actualizarAlumno(alumno);
		mv.addObject("alumno" ,alumno);
		mv.setViewName("redirect:/form");
		return mv;		
		
	}
	
	@RequestMapping(value = "eliminar", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView eliminar(Integer id){
		
		log.info("Ingreso a :" + this.getClass().getName());
		ModelAndView mv=new ModelAndView();
		induccionServicio.eliminarAlumno(id);	
		
		mv.setViewName("redirect:/form");
		return mv;
	}
	
	@RequestMapping(value = "registrar", method = {RequestMethod.GET})
	public ModelAndView registrar(){		
		
		log.info("Ingreso a :" + this.getClass().getName());
		ModelAndView mv = new ModelAndView();		
		return mv;
		
	}
	
	@RequestMapping(value = "registrar", method = {RequestMethod.POST})
	public ModelAndView registrar(@Valid Alumno alumno,BindingResult result){
					
		log.info("Ingreso a :" + this.getClass().getName());
		
		this.validacionAlumno.validate(alumno, result);
		if(result.hasErrors()){
			ModelAndView mv = new ModelAndView();		
			mv.addObject("errores", result.getAllErrors());
			return mv;
			
		}else{
			
			induccionServicio.insertarAlumno(alumno);
			
			return new ModelAndView("redirect:/form");
		}		
				
	}
	
	@RequestMapping(value = "menu", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView menu() {		
	
		log.info("Ingreso a :" + this.getClass().getName());
		
			
		ModelAndView mv = new ModelAndView();	
		return mv;		
		
	}
}
