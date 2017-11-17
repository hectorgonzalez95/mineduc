package cl.mineduc.induccion.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.mineduc.induccion.repo.MessageRepo;

@Service
public class DefaultService {
	private static Logger logger = LogManager.getLogger(DefaultService.class);
	
	@Autowired 
	private Environment env;
	
	@Autowired
	private JsonMessageConverter jsonConverter;
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired 
	private RestTemplate restTemplate;
	


}