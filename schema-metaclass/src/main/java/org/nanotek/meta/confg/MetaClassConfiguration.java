package org.nanotek.meta.confg;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootConfiguration
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
	JdbcTemplate jdbcTemplate(@Autowired DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
}
