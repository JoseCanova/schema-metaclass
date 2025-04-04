package org.nanotek.meta.service.test;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.rdbms.service.SchemaCrawlerForeignKeyService;
import org.nanotek.meta.rdbms.service.SchemaCrawlerRdbmsMetaClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//TODO: implement unit test.
@SpringBootTest
public class SchemaCrawlerForeignKeyServiceTest {

	@Autowired
	SchemaCrawlerForeignKeyService schemaCrawlerForeignKeyService;
	
	@Autowired
	SchemaCrawlerRdbmsMetaClassService schemaCrawlerRdbmsMetaClassService;
	
	
	public SchemaCrawlerForeignKeyServiceTest() {
	}

	
	@Test 
	public void testSchemaCrawlerForeignKeyService() {
		List<RdbmsMetaClass> metClassList = schemaCrawlerRdbmsMetaClassService.getMetaClassList();
		metClassList.forEach(mc -> {
			List<?> result = schemaCrawlerForeignKeyService.getMetaClassForeignKeys(mc, metClassList);
			System.err.println(result);
		});
	}
}
