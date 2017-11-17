package cl.mineduc.induccion;

import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

public class SessionInitializer extends AbstractHttpSessionApplicationInitializer {

	public SessionInitializer() {
		super(HttpSessionConfig.class);
	}
}