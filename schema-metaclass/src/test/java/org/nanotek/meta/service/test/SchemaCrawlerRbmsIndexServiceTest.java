package org.nanotek.meta.service.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.RdbmsIndex;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.rdbms.service.SchemaCrawlerRbmsIndexService;
import org.nanotek.meta.rdbms.service.SchemaCrawlerRdbmsMetaClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SchemaCrawlerRbmsIndexServiceTest {

	@Autowired
	SchemaCrawlerRdbmsMetaClassService schemaCrawlerRdbmsMetaClassService;
	
	@Autowired
	SchemaCrawlerRbmsIndexService schemaCrawlerRbmsIndexService;
	
	public SchemaCrawlerRbmsIndexServiceTest() {
	}
	
	@Test
	void testSchemaCrawlerRbmsIndexService(){
		assertNotNull(schemaCrawlerRdbmsMetaClassService);
		List<RdbmsMetaClass> rdbmsMetaClassList = schemaCrawlerRdbmsMetaClassService.getMetaClassList();
		assertNotNull(schemaCrawlerRbmsIndexService);
		rdbmsMetaClassList
			.forEach(r ->{
				List<RdbmsIndex> indexes = schemaCrawlerRbmsIndexService
												.getRdbmsIndexList(r);
				assertTrue(indexes.size()>0);
				System.err.println(indexes.toString());
			});
	}

}
