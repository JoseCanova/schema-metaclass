package org.nanotek.meta.service.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//TODO:Finish implementation of the test.
@SpringBootTest
public class SchemaCrawlerServiceTest {

	
	@Autowired
	private SchemaCrawlerService schemaCrawlerService;
	
	@Test
	void testSchemaMetaClassPersistence() {
		
		assertNotNull(schemaCrawlerService);
		
	}
	
}
