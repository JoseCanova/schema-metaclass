package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.rdbms.service.SchemaCrawlerDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schemacrawler.schema.Catalog;
import schemacrawler.schema.Table;
import schemacrawler.schemacrawler.LoadOptionsBuilder;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.tools.utility.SchemaCrawlerUtility;

@SpringBootTest
public class DataSourceTest {

	@Autowired
	SchemaCrawlerDataSourceService schemaCrawlerDataSourceService;
	
	@Autowired
	SchemaInfoLevelBuilder schemaInfoLevelBuilder;
	
	@Autowired 
	LoadOptionsBuilder loadOptionsBuilder;
	
	@Autowired 
	SchemaCrawlerOptions schemaCrawlerOptions;
	
	public DataSourceTest() {
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
	
	@Test
	void testSchemaCrawlerPostgresConnectionAndTablesRetrieval() throws SQLException {
		assertNotNull(schemaCrawlerOptions);
		final Catalog  catalog = SchemaCrawlerUtility
					.getCatalog(schemaCrawlerDataSourceService.getDatabaseConnectionSource(), schemaCrawlerOptions);
		Collection<schemacrawler.schema.Table> tables = catalog.getTables();
		
		List<Table> theTablesList =  tables.parallelStream()
						.filter(t1 -> t1.getColumns().size()>0)
						//.map(t -> processMetaClass(t))
						//.filter(m -> m.isPresent())
						//.map(m->m.get())
						.collect(Collectors.toList());
		assertNotNull(theTablesList);
		assertTrue(theTablesList.size() >= 0);
	}
	
}
