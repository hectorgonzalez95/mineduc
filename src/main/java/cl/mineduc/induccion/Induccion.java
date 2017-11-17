/**
 * 
 */
package cl.mineduc.induccion;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cl.mineduc.induccion.interceptors.MDCInterceptor;

/**
 * @author Alvaro Tellez
 *
 */
@SpringBootApplication
public class Induccion {
	private static Logger logger = LogManager.getLogger(Induccion.class);
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws Exception {
		MDCInterceptor.appIsBooting();
		Class<?>[] configClasses = {InduccionConfiguration.class, InduccionSecurityConfiguration.class};
		SpringApplication.run(configClasses, args);		
		logger.info(Induccion.class.getCanonicalName()+ " has debug enabled :"+logger.isDebugEnabled());		
	}
}
