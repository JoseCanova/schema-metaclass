package org.nanotek.meta.service.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.nanotek.meta.rdbms.service.SchemaCrawlerRdbmsMetaClassAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//TODO: Finish unit test.
@SpringBootTest
public class SchemaCrawlerRdbmsMetaClassAttributeServiceTest {

	@Autowired
	SchemaCrawlerRdbmsMetaClassAttributeService schemaCrawlerRdbmsMetaClassAttributeService;
	
	void testSchemaCrawlerRdbmsMetaClassAttributeService() {
		assertNotNull(schemaCrawlerRdbmsMetaClassAttributeService);
	}
	
}
