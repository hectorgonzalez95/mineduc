package cl.mineduc.induccion.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.induccion.modelo.Usuario;
import cl.mineduc.induccion.services.InduccionService;


@Controller
@RequestMapping("alumno")
public class AlumnoController {

	private final Logger log = Logger.getLogger(AlumnoController.class);
	
	@Autowired
	private InduccionService induccionServicio;	
	
	@RequestMapping(value = "form", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView form(@RequestParam(required = false) Integer error){		
		
		log.info("Ingreso a :" + this.getClass().getName());
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("error",error);	
		List<Usuario> datos = induccionServicio.obtenerUsuarios();
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
	public ModelAndView edit(Usuario usuario) {
		
		log.info("Ingreso a :" + this.getClass().getName());
		
		ModelAndView mv = new ModelAndView();	
		induccionServicio.actualizarUsuario(usuario);
		mv.addObject("usuario" ,usuario);
		mv.setViewName("redirect:/form");
		return mv;		
		
	}
	
	@RequestMapping(value = "eliminar", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView eliminar(Integer id){
		
		log.info("Ingreso a :" + this.getClass().getName());
		ModelAndView mv=new ModelAndView();
		induccionServicio.eliminarUsuario(id);	
		
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
	public ModelAndView registrar(@ModelAttribute Usuario usuario){
					
		log.info("Ingreso a :" + this.getClass().getName());		
		
		induccionServicio.insertarUsuario(usuario);
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("redirect:/form");
		return mv;
		
	}
	
	@RequestMapping(value = "menu", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView menu() {		
	
		log.info("Ingreso a :" + this.getClass().getName());
		
			
		ModelAndView mv = new ModelAndView();	
		return mv;		
		
	}
}
