package cl.mineduc.induccion.repo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import cl.mineduc.framework2.exceptions.MineducException;
import cl.mineduc.induccion.mappers.MessageMapper;
import cl.mineduc.induccion.modelo.Alumno;
import cl.mineduc.induccion.modelo.Curso;
import cl.mineduc.induccion.modelo.WorkerMessage;

@Repository("messagesRepo")
public class MessageRepo {
	private static Logger logger = LogManager.getLogger(MessageRepo.class);

		@Autowired
		private MessageMapper messageMapper;		
		

		public void insertMessage(WorkerMessage mensaje){
			if (mensaje == null){
				throw new MineducException("No se puede ingresar un mensaje vacia.");
			}
			try {
				messageMapper.insertMessage(mensaje);
			} catch (DataAccessException e) {
				logger.error("Error de base de datos al ingrear una entidad", e);
				throw new MineducException("Error de base de datos al ingrear una entidad", e);
			}
		}
		
		public void insertarAlumno(Alumno alumno){
			try{
				messageMapper.insertarAlumno(alumno);
				
			}catch (DataAccessException ex){
				logger.error("Error AlumnoRepositorio metodo insertarAlumno parametros :" + alumno +" Fulltrace " + ex);
				throw new MineducException("Error interno, intente nuevamente ",ex);
			}
		}
}