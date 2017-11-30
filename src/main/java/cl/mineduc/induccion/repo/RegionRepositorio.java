package cl.mineduc.induccion.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cl.mineduc.induccion.mappers.MessageMapper;

@Repository("RegionRepositorio")
public class RegionRepositorio {

	@Autowired
	private MessageMapper regionMappers;	
}
