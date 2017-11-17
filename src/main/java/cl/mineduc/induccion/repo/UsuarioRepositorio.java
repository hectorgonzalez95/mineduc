package cl.mineduc.induccion.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.framework2.exceptions.MineducException;
import cl.mineduc.induccion.mappers.MessageMapper;
import cl.mineduc.induccion.modelo.Usuario;


@Repository("UsuarioRepositorio")
public class UsuarioRepositorio {
	
	
	@Autowired
	private MessageMapper usuarioMappers;	

	
	public void insertarUsuario(Usuario usuario){
		try{
			usuarioMappers.insertarUsuario(usuario);
			
		}catch (DataAccessException ex){			
			throw new MineducException("Error interno, intente nuevamente ",ex);
		}
	}

	public List<Usuario> obtenerUsuarios(){
		try{
			return usuarioMappers.obtenerUsuarios();
			
		}catch (DataAccessException ex){
			throw new MineducException("Error interno, intente nuevamente ",ex);
		}
				
	}

	public Usuario obtenerUsuario(Usuario usuario) {
		try{
			return usuarioMappers.obtenerUsuario(usuario);
		}catch(DataAccessException e){
			throw new MineducException("Error al obtener usuario ",e);
		}
	}

	public void eliminarUsuario(Integer id) {
		try{
			usuarioMappers.eliminarUsuario(id);
		}catch(DataAccessException e){
			throw new MineducException("Error al eliminar usuario ",e);
		}
	}

	public void actualizarUsuario(Usuario usuario) {
		try{
			usuarioMappers.actualizarUsuario(usuario);
		}catch(DataAccessException e){
			throw new MineducException("Error al actualizar usuario ",e);
		}
		
	}

}
