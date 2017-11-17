package cl.mineduc.induccion.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cl.mineduc.induccion.modelo.WorkerMessage;
import cl.mineduc.induccion.repo.MessageRepo;

@Component
public class DefaultRequestListener implements MessageListener{
	private static Logger logger = LogManager.getLogger(DefaultRequestListener.class);
	
	@Autowired @Qualifier("messagesRepo")
	MessageRepo messagesRepo;
	
	@Override
	public void onMessage(Message message) {
		if(!(message==null)){
			String strJson = new String(message.getBody());
			logger.debug("Mensaje Recibido ---> {} ",strJson);
			logger.debug("Persistiendo mensaje");
			
			WorkerMessage mensaje = new WorkerMessage();
			mensaje.setMessage(strJson);
			messagesRepo.insertMessage(mensaje);
		}
	}
}