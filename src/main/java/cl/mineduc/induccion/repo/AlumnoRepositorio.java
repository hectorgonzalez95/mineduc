package cl.mineduc.induccion.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.framework2.exceptions.MineducException;
import cl.mineduc.induccion.mappers.MessageMapper;
import cl.mineduc.induccion.modelo.Alumno;


@Repository("AlumnoRepositorio")
public class AlumnoRepositorio {
	
	
	@Autowired
	private MessageMapper alumnoMappers;	

	
	public void insertarAlumno(Alumno alumno){
		try{
			alumnoMappers.insertarAlumno(alumno);
			
		}catch (DataAccessException ex){			
			throw new MineducException("Error interno, intente nuevamente ",ex);
		}
	}

	public List<Alumno> obtenerAlumnos(){
		try{
			return alumnoMappers.obtenerAlumnos();
			
		}catch (DataAccessException ex){
			throw new MineducException("Error interno, intente nuevamente ",ex);
		}
				
	}

	public Alumno obtenerAlumno(Alumno alumno) {
		try{
			return alumnoMappers.obtenerAlumno(alumno);
		}catch(DataAccessException e){
			throw new MineducException("Error al obtener Alumno ",e);
		}
	}

	public void eliminarAlumno(Integer id) {
		try{
			alumnoMappers.eliminarAlumno(id);
		}catch(DataAccessException e){
			throw new MineducException("Error al eliminar Alumno ",e);
		}
	}

	public void actualizarAlumno(Alumno alumno) {
		try{
			alumnoMappers.actualizarAlumno(alumno);
		}catch(DataAccessException e){
			throw new MineducException("Error al actualizar Alumno ",e);
		}
		
	}

}
