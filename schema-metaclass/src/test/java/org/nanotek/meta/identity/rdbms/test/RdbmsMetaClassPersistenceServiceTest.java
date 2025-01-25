package org.nanotek.meta.identity.rdbms.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;
import org.nanotek.meta.rdbms.service.SchemaCrawlerRdbmsMetaClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RdbmsMetaClassPersistenceServiceTest {

	@Autowired
	SchemaCrawlerRdbmsMetaClassService schemaCrawlerRdbmsMetaClassService;
	
	public RdbmsMetaClassPersistenceServiceTest() {
	}

	@Test
	void testSimpleRdbmsMetaClassPersistence() {
		RdbmsMetaClass metaClass = RdbmsMetaClassBuilder.buildSimpleRdbmsMetaClass();
		assertNotNull(schemaCrawlerRdbmsMetaClassService);
		
		RdbmsMetaClassAttribute att = RdbmsMetaClassAttributeBuilder.buildSimpleAttribute();
		metaClass.addMetaAttribute(att);
		
		RdbmsMetaClass persistedMetaClass = schemaCrawlerRdbmsMetaClassService.save(metaClass);
		assertNotNull(persistedMetaClass.getId());
		
		Optional<?> opt = schemaCrawlerRdbmsMetaClassService.findById(persistedMetaClass.getId());
		assertTrue(opt.isPresent());
		
		schemaCrawlerRdbmsMetaClassService.deleteAll();
		
		Optional<?> opt1 = schemaCrawlerRdbmsMetaClassService.findById(persistedMetaClass.getId());
		assertTrue(opt1.isEmpty());

		
	}
}
