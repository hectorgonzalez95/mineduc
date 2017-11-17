package cl.mineduc.induccion.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@RequestMapping("/login")
@Controller
public class LoginController {
	private static Logger logger = LogManager.getLogger(LoginController.class);	
	
	@RequestMapping(value = "login", method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView login(@RequestParam(required = false) Integer error){
		logger.debug("login view request... ");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("error",error);	
		return mv;
	}
		
	
	@RequestMapping(value = "navegador", method = {RequestMethod.GET, RequestMethod.POST })
	public ModelAndView navegador() {
		
		ModelAndView mv = new ModelAndView();
		return mv;
	}
	
	
	
	
	
}