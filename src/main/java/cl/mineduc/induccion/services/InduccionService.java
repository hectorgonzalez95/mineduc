package cl.mineduc.induccion.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.mineduc.induccion.modelo.Usuario;
import cl.mineduc.induccion.repo.UsuarioRepositorio;

@Service
public class InduccionService {
	private static Logger logger = LogManager.getLogger(InduccionService.class);
	
	@Autowired 
	private Environment env;
	
	@Autowired 
	private RestTemplate restTemplate;	

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	public void insertarUsuario(Usuario usuario){
		usuarioRepositorio.insertarUsuario(usuario);
	}
	
	public List<Usuario> obtenerUsuarios(){		
		return usuarioRepositorio.obtenerUsuarios();
	}

	public Usuario obtenerUsuario(Usuario usuario) {
		return usuarioRepositorio.obtenerUsuario(usuario);
	}

	public void eliminarUsuario(Integer id) {
		
		usuarioRepositorio.eliminarUsuario(id);
	}

	public void actualizarUsuario(Usuario usuario) {
		
		usuarioRepositorio.actualizarUsuario(usuario);
	}



}