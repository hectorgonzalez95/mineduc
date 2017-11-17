package cl.mineduc.induccion.controllers;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cl.mineduc.induccion.modelo.auth.Usuario;
@Controller
@RequestMapping("/")
public class MainController {

	@RequestMapping(value = "index", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView index(@RequestParam(required = false) Integer error){
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
	@RequestMapping(value = "home/index", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView home(@RequestParam(required = false) Integer error){
		ModelAndView mv = new ModelAndView();
        mv.addObject("userId", this.getCurrentUser().getLogin() );	
		return mv;
	}
	
	
	private Usuario getCurrentUser() {
		Usuario loggedUser = null;
		if(!(SecurityContextHolder.getContext().getAuthentication().getDetails()==null)){
			loggedUser = (Usuario) SecurityContextHolder.getContext().getAuthentication().getDetails();
		}else{
			if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal()==null)){
				loggedUser = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
		}
		return loggedUser;
	}
}