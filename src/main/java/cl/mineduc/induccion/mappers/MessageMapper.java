package cl.mineduc.induccion.mappers;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cl.mineduc.induccion.modelo.Usuario;
import cl.mineduc.induccion.modelo.WorkerMessage;

public interface MessageMapper {
	
	WorkerMessage getMessage(@Param(value = "id") Integer idEntidad);
	void insertMessage(@Param(value = "message") WorkerMessage mensaje);
	
	
	//Insert Usuario
	void insertarUsuario(Usuario usuario);
	
	//select
	List<Usuario> obtenerUsuarios();
	
	//update
	Usuario obtenerUsuario(Usuario usuario);
	
	//delete
	void eliminarUsuario(Integer id);
	
	void actualizarUsuario(Usuario usuario);
	
	
}