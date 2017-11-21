package cl.mineduc.induccion.mappers;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import cl.mineduc.induccion.modelo.Alumno;
import cl.mineduc.induccion.modelo.Curso;
import cl.mineduc.induccion.modelo.WorkerMessage;

public interface MessageMapper {
	
	WorkerMessage getMessage(@Param(value = "id") Integer idEntidad);
	void insertMessage(@Param(value = "message") WorkerMessage mensaje);
	
	
	//Insert Alumno
	void insertarAlumno(Alumno alumno);
	
	//select Alumno
	List<Alumno> obtenerAlumnos();
	
	//update Alumno
	Alumno obtenerAlumno(Alumno alumno);
	
	//delete Alumno
	void eliminarAlumno(Integer id);
	
	
	void actualizarAlumno(Alumno alumno);
	
	List<Alumno> obtenerAlumno();
	
	
//	===================Cursos====================
	
	void insertarCurso(Curso curso);
		
	List<Curso> obtenerCursos();
	
	Curso obtenerCurso(Curso curso);
	
	void eliminarCurso(Integer id);
	
	void actualizarCurso(Curso curso);
	
	List<Curso> obtenerCurso();
}