package org.nanotek.meta.config;

import javax.sql.DataSource;

import org.nanotek.meta.constants.SystemStaticMessageSource;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import schemacrawler.schemacrawler.LoadOptionsBuilder;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaCrawlerOptionsBuilder;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;

@SpringBootConfiguration
@ComponentScan(basePackages = {"org.nanotek.meta.rdbms.service" ,"org.nanotek.meta.util" })
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
				.setRetrieveForeignKeys(false)
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
	
}
