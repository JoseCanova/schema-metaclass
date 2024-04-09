package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schemacrawler.schema.Catalog;
import schemacrawler.schemacrawler.LoadOptionsBuilder;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.tools.utility.SchemaCrawlerUtility;

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
	
	
	//TODO: finish this unit test. 
	void prepareThisTest() {
		Connection connection = dataSource.getConnection();
		
		final Catalog  catalog = SchemaCrawlerUtility.getCatalog(connection, options);
		Collection<schemacrawler.schema.Table> tables = catalog.getTables();
		
		return tables.parallelStream()
						.filter(t1 -> t1.getColumns().size()>0)
						.map(t -> processMetaClass(t))
						.filter(m -> m.isPresent())
						.map(m->m.get())
						.collect(Collectors.toList());
	}
}
