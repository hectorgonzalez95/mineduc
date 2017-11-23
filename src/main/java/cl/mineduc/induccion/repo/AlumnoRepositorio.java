package cl.mineduc.induccion.repo;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.framework2.exceptions.MineducException;
import cl.mineduc.induccion.mappers.MessageMapper;
import cl.mineduc.induccion.modelo.Alumno;
import cl.mineduc.induccion.modelo.Curso;


@Repository("AlumnoRepositorio")
public class AlumnoRepositorio {
	
	private static final Logger logger = LogManager.getLogger(AlumnoRepositorio.class);
	
	@Autowired
	private MessageMapper alumnoMappers;	
	
	@Autowired
	private MessageMapper cursoMappers;

	
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
			logger.error("Error interno, intente nuevamente ",ex);
			throw new MineducException("Error interno, intente nuevamente ",ex);
		}
				
	}

	public Alumno obtenerAlumno2(Alumno alumno) {
		try{
			return alumnoMappers.obtenerAlumno2(alumno);
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

	public List<Curso> obtenerCursos() {
		try{
			return cursoMappers.obtenerCursos();
			
		}catch (DataAccessException ex){
			throw new MineducException("Error interno, intente nuevamente ",ex);
		}
	}

}
