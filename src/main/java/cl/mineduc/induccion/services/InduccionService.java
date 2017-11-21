package cl.mineduc.induccion.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.mineduc.induccion.modelo.Alumno;
import cl.mineduc.induccion.modelo.Curso;
import cl.mineduc.induccion.repo.AlumnoRepositorio;
import cl.mineduc.induccion.repo.CursoRepositorio;

@Service
public class InduccionService {
	private static Logger logger = LogManager.getLogger(InduccionService.class);
	
	@Autowired 
	private Environment env;
	
	@Autowired 
	private RestTemplate restTemplate;	

	@Autowired
	private AlumnoRepositorio alumnoRepositorio;
	
	@Autowired
	private CursoRepositorio cursoRepositorio;
	
	public void insertarAlumno(Alumno alumno){
		alumnoRepositorio.insertarAlumno(alumno);
	}
	
	public Alumno obtenerAlumno(Alumno alumno) {
		return alumnoRepositorio.obtenerAlumno(alumno);
	}

	public void eliminarAlumno(Integer id) {
		
		alumnoRepositorio.eliminarAlumno(id);
	}

	public void actualizarAlumno(Alumno alumno) {
		
		alumnoRepositorio.actualizarAlumno(alumno);
	}

	public List<Alumno> obtenerAlumnos() {
		return alumnoRepositorio.obtenerAlumnos();
		
	}

	public void insertarCurso(Curso curso) {
		cursoRepositorio.insertarCurso(curso);
		
	}



}