package org.nanotek.meta.rdbms.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.stereotype.Service;

import us.fatehi.utility.datasource.DatabaseConnectionSource;
import us.fatehi.utility.datasource.DatabaseConnectionSourceBuilder;
import us.fatehi.utility.datasource.MultiUseUserCredentials;

//TODO: implement a sample schemacrawler datasource properties 
//for the current RDBMS unders usage in this case POSTGRESQL
/**
 * 
 */
@Service
@ConfigurationProperties(prefix = "spring.datasource") 
@ConfigurationPropertiesScan 
public class SchemaCrawlerDataSourceService {

	private String jdbcUrl;
	private String url;
	private String username;
	private String password;
	
	public SchemaCrawlerDataSourceService(){}
	
	public SchemaCrawlerDataSourceService(String jdbcUrl, String url, String username, String password) {
		super();
		this.jdbcUrl = jdbcUrl;
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	} 

	public MultiUseUserCredentials getUserCredentials() {
		return new MultiUseUserCredentials(username, password);
	}
	
	
	public DatabaseConnectionSource getDatabaseConnectionSource() {
		return DatabaseConnectionSourceBuilder.builder(jdbcUrl)
				.withConnectionUrl(url).withUserCredentials(getUserCredentials())
				.build();
	}
}
