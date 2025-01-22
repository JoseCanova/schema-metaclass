package org.nanotek.meta.service.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.rdbms.service.SchemaCrawlerRdbmsMetaClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//TODO:finish unit test
@SpringBootTest
public class SchemaCrawlerRdbmsMetaClassServiceTest {

	
	@Autowired
	SchemaCrawlerRdbmsMetaClassService schemaCrawlerRdbmsMetaClassService;
	
	@Test 
	void testSchemaCrawlerRdbmsMetaClassService() {
		assertNotNull(schemaCrawlerRdbmsMetaClassService);
	}
}
