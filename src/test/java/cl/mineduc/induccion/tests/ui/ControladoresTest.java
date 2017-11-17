package cl.mineduc.induccion.tests.ui;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import cl.mineduc.induccion.InduccionConfiguration;
import cl.mineduc.induccion.InduccionSecurityConfiguration;
import cl.mineduc.induccion.modelo.auth.Usuario;
import cl.mineduc.induccion.modelo.auth.Sistema;

@Test
@SpringBootTest( 
		properties={ "logstash.host=localhost","logstash.port=4560", "spring.active.profiles=native" } ,
		webEnvironment = WebEnvironment.MOCK , 
		classes = {InduccionConfiguration.class,InduccionSecurityConfiguration.class}
		)
public class ControladoresTest extends AbstractTestNGSpringContextTests{

	private final String GOOGLE_CHROME_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext wac;
	
	@Autowired
	private Environment env;
	
	
	@BeforeClass
	public void setup() throws Exception {

		String nombreSistema = env.getProperty("cl.mineduc.induccion.aplicacion.apilogin");
		Usuario usuarioDePrueba = new Usuario();
		usuarioDePrueba.setLogin("usuarioGagá");
		usuarioDePrueba.setSistemas(new ArrayList<Sistema>());

		logger.info(usuarioDePrueba);
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.defaultRequest(get("/").header("User-Agent", GOOGLE_CHROME_AGENT)
						.with(SecurityMockMvcRequestPostProcessors.user(usuarioDePrueba)))
				.apply(SecurityMockMvcConfigurers.springSecurity()).build();
	}	
	
	@Test()
	public void formLoginFail() throws Exception {
		mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin("/login/do_login")
				.user("txtUsuario", "hola")
				.password("txtClave", "xxx"))
		.andExpect(SecurityMockMvcResultMatchers.unauthenticated());
	}	
	
	@Test()
	public void NavegacionBasica() throws Exception {
		mockMvc.perform(get("/index")).andExpect(status().isOk());	
		mockMvc.perform(get("/login/login")).andExpect(status().isOk());
		mockMvc.perform(get("/login/navegador")).andExpect(status().isOk());	
	}
}