package cl.mineduc.induccion.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.framework2.exceptions.MineducException;
import cl.mineduc.induccion.mappers.MessageMapper;
import cl.mineduc.induccion.modelo.Curso;
import cl.mineduc.induccion.modelo.CursoEliminar;
import cl.mineduc.induccion.modelo.Otro;

@Repository("CursoRepositorio")
public class CursoRepositorio {

	
	@Autowired
	private MessageMapper cursoMappers;	

	
	public void insertarCurso(Otro otro){
		try{
			cursoMappers.insertarCurso(otro);
			
		}catch (DataAccessException ex){			
			throw new MineducException("Error interno, intente nuevamente ",ex);
		}
	}

	public List<Curso> obtenerCursos(){
		try{
			return cursoMappers.obtenerCursos();
			
		}catch (DataAccessException ex){
			throw new MineducException("Error interno, intente nuevamente ",ex);
		}
				
	}

	public Curso obtenerCurso(Curso curso) {
		try{
			return cursoMappers.obtenerCurso(curso);
		}catch(DataAccessException e){
			throw new MineducException("Error al obtener Curso ",e);
		}
	}

	public void eliminarCurso(Integer id) {
		try{
			cursoMappers.eliminarCurso(id);
		}catch(DataAccessException e){
			throw new MineducException("Error al eliminar Curso ",e);
		}
	}

	public void actualizarCurso(Curso curso) {
		try{
			cursoMappers.actualizarCurso(curso);
		}catch(DataAccessException e){
			throw new MineducException("Error al actualizar Curso ",e);
		}
		
	}

	public void eliminarCursos(CursoEliminar cursoEliminar) {
		try{
			cursoMappers.eliminarCursos(cursoEliminar);
		}catch(DataAccessException e){
			throw new MineducException("Error al eliminar Curso ",e);
		}
		
	}

	public void editarCurso(Curso curso) {
		try{
			cursoMappers.editarCurso(curso);
		}catch(DataAccessException e){
			throw new MineducException("Error al editar Curso ",e);
		}
		
	}

	public void insertarCursoAjax(Integer id, String curso) {
		try{
			cursoMappers.insertarCursoAjax(id,curso);
		}catch(DataAccessException e){
			throw new MineducException("error" ,e);
		}
	}
}
