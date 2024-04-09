package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schemacrawler.schemacrawler.LoadOptionsBuilder;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;

@SpringBootTest
public class DataSourceTest {

	@Autowired 
	DataSource defaultDataSource;
	
	@Autowired
	SchemaInfoLevelBuilder schemaInfoLevelBuilder;
	
	@Autowired 
	LoadOptionsBuilder loadOptionsBuilder;
	
	@Autowired 
	SchemaCrawlerOptions schemaCrawlerOptions;
	
	public DataSourceTest() {
	}

	@Test
	void testDefaultDataSource() {
		assertNotNull(defaultDataSource);
	}
	
	@Test
	void testDefaultDataSourceConnection() throws SQLException {
		assertNotNull(defaultDataSource);
		Connection con = defaultDataSource.getConnection();
		assertNotNull(con);
	}
	
	@Test
	void testSchemaInfoLevelBuilder() {
		assertNotNull(schemaInfoLevelBuilder);
	}
	
	@Test
	void testLoadOptionsBuilder() {
		assertNotNull(loadOptionsBuilder);
	}
	
	@Test
	void testSchemaCrawlerOptions() {
		assertNotNull(schemaCrawlerOptions);
	}
}
