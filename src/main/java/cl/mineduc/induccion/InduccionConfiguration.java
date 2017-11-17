/**
 * 
 */
package cl.mineduc.induccion;

import javax.sql.DataSource;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.XADataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.annotation.AnnotationMethodHandlerExceptionResolver;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import cl.mineduc.framework2.web.interceptors.UserAgentInterceptor;
import cl.mineduc.induccion.interceptors.InduccionAddCommonVariablesInterceptor;
import cl.mineduc.induccion.interceptors.MDCInterceptor;
import cl.mineduc.induccion.listeners.DefaultRequestListener;
import cl.mineduc.induccion.mappers.MessageMapper;
import cl.mineduc.induccion.services.InduccionCommonModelService;

/**
 * @author Rodrigo Alvarez, Alvaro Tellez
 *
 */
@Configuration
@EnableAutoConfiguration(exclude={XADataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"cl.mineduc.induccion.controllers","cl.mineduc.induccion.services","cl.mineduc.induccion.mappers","cl.mineduc.induccion.repo","cl.mineduc.induccion.clients"})
@EnableAsync
@EnableRabbit
public class InduccionConfiguration extends WebMvcConfigurerAdapter{
	@Autowired 
	private Environment env;
	
@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}
	
	@Bean
	public HandlerExceptionResolver annotationMethodHandlerExceptionResolver() {
		AnnotationMethodHandlerExceptionResolver resolver = new AnnotationMethodHandlerExceptionResolver();
		resolver.setOrder(1);
		return resolver;
	}
	@Bean
	public JsonMessageConverter jsonConverter(){
		return new JsonMessageConverter();
	}
	
	@Bean
	public ObjectMapper jsonMapper(){
		return new ObjectMapper();
	}
	
	/** web mvc mineduc framework conf **/
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource(){
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("classpath:application-induccion-messages");
		return bundle;
	}
	
	@Bean(name="commonService")
	public InduccionCommonModelService commonModelService(){
		InduccionCommonModelService service = new InduccionCommonModelService();
		service.setDevelopIPs(env.getProperty("cl.mineduc.debug.ips", "127.0.0.1,0:0:0:0:0:0:0:1"));
		return service;
	}	
	
	@Bean
	public HandlerInterceptor addCommonVariablesInterceptor(){
		InduccionAddCommonVariablesInterceptor interceptor = new InduccionAddCommonVariablesInterceptor();
		interceptor.setCommonService(commonModelService());
		return interceptor;
	}
	@Bean
	public HandlerInterceptor addMDCInterceptor() {
		return new MDCInterceptor();
	}
	
	@Bean
	public HandlerInterceptor userAgentInterceptor(){
		UserAgentInterceptor uaInterceptor = new UserAgentInterceptor();
		return uaInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(this.addMDCInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(this.addCommonVariablesInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(this.userAgentInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}	
	/** web mvc framework conf **/	
	
	@Bean
	public AmqpAdmin rabbitAdmin(ConnectionFactory connectionFactory){
		return new RabbitAdmin(connectionFactory);
	}
	
	@Bean
	public Queue defaultQueue(){
		return new Queue(env.getProperty("appname.queues.default.in","appname.queues.default.in"),true);
	}

	@Bean
	public DirectExchange defaultExchange(){
		DirectExchange exchange = new DirectExchange(env.getProperty("appname.exchange","appname.exchange"),true,false);

		return exchange;
	}


	@Bean
	public Binding testBinding(DirectExchange exchange){
		return BindingBuilder.bind(defaultQueue())
																					.to(exchange)
																					.with(env.getProperty("appname.queues.default.in","appname.queues.default.in"));
	}

	@Bean 
	public DefaultRequestListener defaultListener(){
		return new DefaultRequestListener();
	}

	@Bean
	public SimpleMessageListenerContainer inicializacionContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueues(defaultQueue());
		container.setAutoDeclare(true);
		container.setMessageListener(defaultListener());
		return container;
	}
	
	@Bean
	@Primary
	public DataSource dataSourceInduccion(){
		HikariConfig config = new HikariConfig();
		config.setPoolName("HickariPool Induccion - induccion");
		config.setDriverClassName(env.getProperty("cl.mineduc.induccion.datasource.driver-class-name"));
		config.setJdbcUrl(env.getProperty("cl.mineduc.induccion.datasource.url"));
		config.setUsername(env.getProperty("cl.mineduc.induccion.datasource.username"));
		config.setPassword(env.getProperty("cl.mineduc.induccion.datasource.password"));
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.setMinimumIdle(1);
		config.setMaximumPoolSize(20);

		return new HikariDataSource(config);
	}
	
	@Bean(name="transactionManagerInduccion")
	public PlatformTransactionManager transactionManagerInduccion(){
		return new DataSourceTransactionManager(dataSourceInduccion());
	}
	
	@Bean(name="sqlSessionFactoryInduccion")
	@Primary
	public SqlSessionFactory sqlSessionFactoryInduccion() throws Exception {
	    SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
	    sessionFactory.setDataSource(dataSourceInduccion());
	    sessionFactory.setTypeHandlersPackage("cl.mineduc.induccion.typehandlers");
	    Resource[] arrResource = new PathMatchingResourcePatternResolver().getResources("maps/postgresql/*.xml");
	    sessionFactory.setMapperLocations(arrResource);	    
	    return sessionFactory.getObject();
	}
	
	@Bean(name="MapperFactoryInduccion")
  public MapperFactoryBean<MessageMapper> mapperFactoryInduccion() throws Exception{
      MapperFactoryBean<MessageMapper> mapperFactory = new MapperFactoryBean<MessageMapper>();
      /** binding de la interface con sqlsession **/
      mapperFactory.setMapperInterface(cl.mineduc.induccion.mappers.MessageMapper.class);
      mapperFactory.setSqlSessionFactory(sqlSessionFactoryInduccion());
      return mapperFactory;
  }
	
	/**
	 * RestTemplate beans for API consumption
	 */
	@Bean
	public Credentials credentials(){
		return new UsernamePasswordCredentials("", ""); // en caso de usar basic http auth
	}
	
	@Bean
	public CredentialsProvider credentialsProvider(){
		BasicCredentialsProvider provider = new BasicCredentialsProvider();
		provider.setCredentials(AuthScope.ANY,credentials());
		return provider;
	}
	
	@Bean
	public HttpComponentsClientHttpRequestFactory httpFactory(){
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
	  httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider());
	  CloseableHttpClient httpClient = httpClientBuilder.build();
	  return new HttpComponentsClientHttpRequestFactory(httpClient);		
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate(httpFactory());
	}	
}
