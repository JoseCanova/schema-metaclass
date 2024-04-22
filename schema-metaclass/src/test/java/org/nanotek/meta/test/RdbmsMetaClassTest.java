package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.sql.DataSource;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.rdbms.service.RdbmsMetaClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Table;
import schemacrawler.schemacrawler.LoadOptionsBuilder;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.tools.utility.SchemaCrawlerUtility;

@SpringBootTest
public class RdbmsMetaClassTest {

	@Autowired 
	DataSource defaultDataSource;
	
	@Autowired
	SchemaInfoLevelBuilder schemaInfoLevelBuilder;
	
	@Autowired 
	LoadOptionsBuilder loadOptionsBuilder;
	
	@Autowired 
	SchemaCrawlerOptions schemaCrawlerOptions;
	
	@Autowired 
	RdbmsMetaClassService rdbmsMetaClassService;
	
	public RdbmsMetaClassTest() {
	}

	@Test
	@Order(5)
	void testRdbmsMetaClassService() throws SQLException {
		prepareTest();
		assertNotNull(rdbmsMetaClassService);
		assertNotNull(defaultDataSource);
		Connection connection = defaultDataSource.getConnection();
		
		assertNotNull(schemaCrawlerOptions);
		final Catalog  catalog = SchemaCrawlerUtility.getCatalog(connection, schemaCrawlerOptions);
		Collection<schemacrawler.schema.Table> tables = catalog.getTables();
		
		List<Table> theTablesList =  tables.parallelStream()
						.filter(t1 -> t1.getColumns().size()>0)
						//.map(t -> processMetaClass(t))
						//.filter(m -> m.isPresent())
						//.map(m->m.get())
						.collect(Collectors.toList());
		assertNotNull(theTablesList);
		assertTrue(theTablesList.size() >= 1);
		List<MetaClass<?,?>> metaClassList = new ArrayList<MetaClass<?,?>>();
		theTablesList.forEach(t -> metaClassList.add(rdbmsMetaClassService.createMetaClass(t)));
		assertTrue(theTablesList.size() == metaClassList.size());
		closeTest();
	}
	
	@Test
	@Order(4)
	void testRdbmsMetaClassAttributeCration() throws SQLException {
		prepareTest();
		assertNotNull(rdbmsMetaClassService);
		assertNotNull(defaultDataSource);
		Connection connection = defaultDataSource.getConnection();
		
		assertNotNull(schemaCrawlerOptions);
		final Catalog  catalog = SchemaCrawlerUtility.getCatalog(connection, schemaCrawlerOptions);
		Collection<schemacrawler.schema.Table> tables = catalog.getTables();
		
		List<Table> theTablesList =  tables.parallelStream()
						.filter(t1 -> t1.getColumns().size()>0)
						//.map(t -> processMetaClass(t))
						//.filter(m -> m.isPresent())
						//.map(m->m.get())
						.collect(Collectors.toList());
		assertNotNull(theTablesList);
		assertTrue(theTablesList.size() >= 1);
		List<MetaClass<?,?>> metaClassList = new ArrayList<>();
		theTablesList.forEach(t -> metaClassList.add(rdbmsMetaClassService.createMetaClass(t)));
		assertTrue(theTablesList.size() == metaClassList.size());
		closeTest();
	}

	private void closeTest() throws SQLException {
			assertNotNull(defaultDataSource);
			Connection connection = defaultDataSource.getConnection();		
	        Statement stmt = connection.createStatement();
	        String sql = 
	        		"DROP TABLE IF EXISTS accounts;"; 
	        stmt.executeUpdate(sql);
	        connection.close();		
	}

	private void prepareTest() throws SQLException {
		assertNotNull(defaultDataSource);
		Connection connection = defaultDataSource.getConnection();		
        Statement stmt = connection.createStatement();
        String sql = 
        		"CREATE TABLE IF NOT EXISTS accounts (\r\n"
        		+ "  user_id SERIAL PRIMARY KEY, \r\n"
        		+ "  username VARCHAR (50) UNIQUE NOT NULL, \r\n"
        		+ "  password VARCHAR (50) NOT NULL, \r\n"
        		+ "  email VARCHAR (255) UNIQUE NOT NULL, \r\n"
        		+ "  created_at TIMESTAMP NOT NULL, \r\n"
        		+ "  last_login TIMESTAMP\r\n"
        		+ ");"; 
        stmt.executeUpdate(sql);
        connection.close();
	} 
}
