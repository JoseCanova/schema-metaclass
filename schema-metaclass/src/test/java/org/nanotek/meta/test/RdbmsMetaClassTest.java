package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
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
		List<MetaClass> metaClassList = new ArrayList<MetaClass>();
		theTablesList.forEach(t -> metaClassList.add(rdbmsMetaClassService.createMetaClass(t)));
		assertTrue(theTablesList.size() == metaClassList.size());
	}
	
	@Test
	void testRdbmsMetaClassAttributeCration() throws SQLException {
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
		List<MetaClass> metaClassList = new ArrayList<MetaClass>();
		theTablesList.forEach(t -> metaClassList.add(rdbmsMetaClassService.createMetaClass(t)));
		assertTrue(theTablesList.size() == metaClassList.size());
	} 
}