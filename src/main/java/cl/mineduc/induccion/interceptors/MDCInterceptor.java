package cl.mineduc.induccion.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cl.mineduc.framework2.utils.StringUtils;

public class MDCInterceptor extends HandlerInterceptorAdapter{
	
	private static final String NOMBRE_SISTEMA = "induccion";
	private static final String NULL = "NULL";
	
	private static final String MDC_X_FORWARDED_FOR = "mdcXForwardedFor";
	private static final String MDC_NOMBRE_SISTEMA = "mdcSistema";
	private static final String MDC_NOMBRE_USUARIO = "mdcUsuario";
	private static final String MDC_J_SESSION_ID = "mdcJavaSessionId";

	@Override
	public void postHandle(HttpServletRequest requestParam, HttpServletResponse responseParam, Object handlerParam,
			ModelAndView modelAndViewParam) throws Exception {
		super.postHandle(requestParam, responseParam, handlerParam, modelAndViewParam);
		String strSessionID = NULL;
		HttpSession sesion = requestParam.getSession(false);
		if (sesion != null) {
			strSessionID = sesion.getId();
		}
		String strUsuario = NULL;
		SecurityContext ctx = SecurityContextHolder.getContext();
		if (ctx != null) {
			Authentication auth = ctx.getAuthentication();
			if (auth != null) {
				strUsuario = auth.getName();
			}
		}
		String ip = requestParam.getHeader("X-Forwarded-For");
		if (StringUtils.isEmpty(ip)) {
			ip = NULL;
		}
		MDC.put(MDC_X_FORWARDED_FOR, ip);
		MDC.put(MDC_J_SESSION_ID, strSessionID);
		MDC.put(MDC_NOMBRE_USUARIO, strUsuario);
		MDC.put(MDC_NOMBRE_SISTEMA, NOMBRE_SISTEMA);
	}
	
	public static void appIsBooting(){
		MDC.put(MDC_X_FORWARDED_FOR, NULL);
		MDC.put(MDC_J_SESSION_ID, NULL);
		MDC.put(MDC_NOMBRE_USUARIO, NULL);
		MDC.put(MDC_NOMBRE_SISTEMA, NOMBRE_SISTEMA);
	}

	public static void setThreadMetadata(String session,String usuario) {
		MDC.put(MDC_X_FORWARDED_FOR, NULL);
		MDC.put(MDC_J_SESSION_ID, session);
		MDC.put(MDC_NOMBRE_USUARIO, usuario);
		MDC.put(MDC_NOMBRE_SISTEMA, NOMBRE_SISTEMA);
	}
}
