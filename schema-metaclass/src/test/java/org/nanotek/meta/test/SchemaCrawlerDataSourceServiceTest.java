package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.rdbms.service.SchemaCrawlerDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SchemaCrawlerDataSourceServiceTest {

	
	@Autowired
	SchemaCrawlerDataSourceService dataSourceService;
	
	@Test
	void testSchemDataSourceService() {
		
		assertNotNull(dataSourceService);
		assertNotNull(dataSourceService.getJdbcUrl());
		assertNotNull(dataSourceService.getUrl());
		assertNotNull(dataSourceService.getUsername());
		assertNotNull(dataSourceService.getPassword());
		assertNotNull(dataSourceService.getUserCredentials());
		assertNotNull(dataSourceService.getDatabaseConnectionSource());
	}
	
}
