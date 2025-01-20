package org.nanotek.meta.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import javax.sql.DataSource;

//import org.bson.codecs.configuration.CodecRegistries;
//import org.bson.codecs.configuration.CodecRegistry;
import org.nanotek.meta.constants.SystemStaticMessageSource;
import org.nanotek.meta.controller.RdbmsMetaClassHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
//import org.springframework.data.mongodb.MongoDatabaseFactory;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import jakarta.persistence.EntityManagerFactory;
import jakarta.validation.Validator;
import schemacrawler.schemacrawler.LoadOptionsBuilder;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaCrawlerOptionsBuilder;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;

//TODO: implement a router function to provide a metaclass based on its id.
@SpringBootConfiguration
@ComponentScan(basePackages = {"org.nanotek.meta.rdbms.service" 
								,"org.nanotek.meta.util",
								"org.nanotek.meta.integration.handler", 
								"org.nanotek.meta.repository"})
public class MetaClassConfiguration {

	public MetaClassConfiguration() {
	}
	
	@Primary
	@Bean(name = "defaultDataSourceProperties")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSourceProperties defaultDataSourceProperties() {
		DataSourceProperties dsp = new DataSourceProperties();
		dsp.setName("portal-data-source");
		return dsp;
	}

	@Primary
	@Bean
	public DataSource defaultDataSource(
			@Qualifier("defaultDataSourceProperties") DataSourceProperties dataSourceProperties) {
		return dataSourceProperties.initializeDataSourceBuilder().build();
	}


	@Bean
	@Primary
	public JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	
	@Bean 
	@Primary
	public SchemaInfoLevelBuilder getSchemaBuilder() {
		return SchemaInfoLevelBuilder.builder()
				.setRetrieveAdditionalColumnAttributes(true)
				.setRetrieveAdditionalColumnMetadata(false)
				.setRetrieveColumnDataTypes(true)
				.setRetrieveForeignKeys(true)
				.setRetrieveIndexes(true)
				.setRetrieveIndexInformation(true)
				.setRetrieveTriggerInformation(false)
				.setRetrievePrimaryKeys(true)
				.setRetrieveTableColumns(true)
				.setRetrieveTables(true);
	}
	
	@Bean
	@Primary
	public LoadOptionsBuilder getLoadOptionsBuilder(@Autowired SchemaInfoLevelBuilder builder) {
		return  LoadOptionsBuilder.builder()
				.withSchemaInfoLevel(builder.toOptions());
	}
	
	@Bean
	@Primary
	public SchemaCrawlerOptions getSchemaCrawlerOptions(@Autowired LoadOptionsBuilder loadOptionsBuilder) {
		return SchemaCrawlerOptionsBuilder.newSchemaCrawlerOptions()
				.withLoadOptions(loadOptionsBuilder.toOptions());
	}
	
	@Bean
	@Primary
	public SystemStaticMessageSource getSystemStaticMessageSour() {
		return new SystemStaticMessageSource();
	}
	
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	@Primary
	public LocalValidatorFactoryBean getLocalValidatorFactoryBean() { 
		LocalValidatorFactoryBean validatorFactoryBean =  new LocalValidatorFactoryBean();
		validatorFactoryBean.setValidationMessageSource(messageSource());
		return validatorFactoryBean;
	}
	
	@Bean
	@Primary
	public MethodValidationPostProcessor methodValidationPostProcessor(@Autowired Validator validator) {
		MethodValidationPostProcessor processor =  new MethodValidationPostProcessor();
		processor.setValidator(validator);
		processor.setProxyTargetClass(false);
		processor.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return processor;
	}
	
	 @Bean
	  public RouterFunction<ServerResponse> routeMetaClassList(@Autowired RdbmsMetaClassHandler rdbmsMetaClassHandler) {

	    return RouterFunctions
	    		.route(GET("/meta-class").and(accept(MediaType.APPLICATION_JSON)), rdbmsMetaClassHandler::getMetaClassList);
	 }
	 
	 @Bean
	  public RouterFunction<ServerResponse> routeTableClassNameList(@Autowired RdbmsMetaClassHandler rdbmsMetaClassHandler) {

	    return RouterFunctions
	    		.route(GET("/table-classname").and(accept(MediaType.APPLICATION_JSON)), rdbmsMetaClassHandler::getTableClassNameList);
	 }
	
	 @Bean
	 public RouterFunction<ServerResponse> routeRdbmsMetaClassByTableClassName(@Autowired RdbmsMetaClassHandler rdbmsMetaClassHandler) {

	    return RouterFunctions
	    		.route(POST("/rdbms-metaclass").and(accept(MediaType.APPLICATION_JSON)), rdbmsMetaClassHandler::getRdbmsMetaClass);
	 }
	 
	 @Bean
	 public Jackson2ObjectMapperBuilder configureObjectMapper() {
			return new Jackson2ObjectMapperBuilder();
	 }
	 
	 
	 //TODO: such bean configuration for codec registry is not working.
		/*
		 * @Bean public CodecRegistry mongoCodecRegistry(@Autowired
		 * MappingMongoConverter mongoConverter) { return
		 * CodecRegistries.fromRegistries( CodecRegistries.fromCodecs(new
		 * BsonJavaClassCodec()), mongoConverter.getCodecRegistry());
		 * 
		 * }
		 * 
		 * @Bean public MongoTemplate mongoTemplate(@Autowired MongoDatabaseFactory
		 * mongoDbFactory) { return new MongoTemplate(mongoDbFactory);
		 * 
		 * }
		 */
	 
	 	@Bean
		@Primary
		public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
			HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
			vendorAdapter.setGenerateDdl(true);
			LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
			factory.setJpaVendorAdapter(vendorAdapter);
			factory.setPackagesToScan("org.nanotek");
			factory.setPersistenceUnitName("music-brainz");
			factory.setDataSource(defaultDataSource(defaultDataSourceProperties()));
			return factory;
		}

		//
		@Bean
		public PlatformTransactionManager transactionManager(@Autowired EntityManagerFactory entityManagerFactory) { 
			JpaTransactionManager txManager = new JpaTransactionManager();
			txManager.setEntityManagerFactory(entityManagerFactory); 
			return txManager; 
		}
}
