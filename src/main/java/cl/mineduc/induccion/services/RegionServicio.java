package cl.mineduc.induccion.services;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.mineduc.framework2.exceptions.MineducException;
import cl.mineduc.induccion.modelo.Region;
import cl.mineduc.induccion.services.RegionServicio;

@Service
public class RegionServicio {
	
	private static Logger logger = LogManager.getLogger(RegionServicio.class);
	
	@Autowired 
	private Environment env;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	RegionServicio regionServicio;
	
	public List<Region> buscarRegiones() {		
		List<Region> listaRegiones = null;
		try{	
			URI uri = URI.create(env.getProperty("cl.mineduc.induccion.api.region.buscarregiones"));
			Region[] regiones = restTemplate.getForObject(uri, Region[].class);
			if(regiones != null) {
				listaRegiones = Arrays.asList(regiones);
			}
		}catch(Exception e ){
			logger.debug(e.getStackTrace());
			throw new MineducException("Error al buscar las regiones.");
		}
		return listaRegiones;	
		
	}
		
}
