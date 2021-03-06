package cl.mineduc.induccion.authentication;

import cl.mineduc.framework2.security.spring.UserService;
import cl.mineduc.induccion.modelo.auth.Usuario;

public class UserServices implements UserService {

	@Override
	public String[] getAuthorities(String userName) {
		String[] roles = {"ROLE_USER"};
		return roles;
	}

	@Override
	public Object getUserDetails(String userName) {
		// Aqui debemos crear o buscar nuestro propio objeto de usuario, este ejemplo solo guarda el nombre 
		// Se debe crear un a clase Usuario y devlover esta en este metodo
		return new Usuario();
	}	
}
