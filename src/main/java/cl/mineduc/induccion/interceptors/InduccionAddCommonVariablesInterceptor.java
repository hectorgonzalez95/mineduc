package cl.mineduc.induccion.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cl.mineduc.induccion.services.InduccionCommonModelService;

public class InduccionAddCommonVariablesInterceptor extends HandlerInterceptorAdapter{
	private static Logger logger = LogManager.getLogger(InduccionAddCommonVariablesInterceptor.class);
	private InduccionCommonModelService commonService;
	
	// Getters
	public InduccionCommonModelService getCommonService() {return commonService;}

	// Setters
	public void setCommonService(InduccionCommonModelService v) {this.commonService = v;}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) throws Exception {
		// Call super first of all
		super.postHandle(request, response, handler, mv);
		logger.debug("adding Induccion common model service");
		if (getCommonService() != null){
			getCommonService().fillModel(request,response,handler,mv);
			logger.debug("request model filled!");
		}
	}
}
