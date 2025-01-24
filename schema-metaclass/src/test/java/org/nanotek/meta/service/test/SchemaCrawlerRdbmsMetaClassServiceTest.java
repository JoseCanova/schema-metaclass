package org.nanotek.meta.service.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.rdbms.service.SchemaCrawlerRdbmsMetaClassService;
import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import schemacrawler.schema.Table;

//TODO:finish unit test
@SpringBootTest
public class SchemaCrawlerRdbmsMetaClassServiceTest {

	
	@Autowired
	SchemaCrawlerRdbmsMetaClassService schemaCrawlerRdbmsMetaClassService;
	
	@Autowired 
	SchemaCrawlerService schemaCrawlerService;
	
	@Test 
	void testSchemaCrawlerRdbmsMetaClassService() {
		assertNotNull(schemaCrawlerRdbmsMetaClassService);
		var metaClasses = testSchemaCrawlerRdbmsMetaClassService_listRetrieval();
		testRdbmsMetaClassAttributes(metaClasses);
		var persistedMetaClasses = testRdbmsMetaClassPersistence(metaClasses);
		
		schemaCrawlerRdbmsMetaClassService.deleteAll();
		schemaCrawlerRdbmsMetaClassService.flush();
	}
	
	List<RdbmsMetaClass> testRdbmsMetaClassPersistence(List<RdbmsMetaClass> metaClasses) {
		
		List<RdbmsMetaClass> persistedMetaClasses = metaClasses
		.stream()
		.map(m ->{
			return schemaCrawlerRdbmsMetaClassService.save(m);
		}).collect(Collectors.toList());
		schemaCrawlerRdbmsMetaClassService.flush();
		return persistedMetaClasses;
	}

	void testRdbmsMetaClassAttributes(List<RdbmsMetaClass> metaClasses) {
		metaClasses
		.stream()
		.forEach(m -> assertTrue(m.getMetaAttributes().size() > 0));
		}

	List<RdbmsMetaClass> testSchemaCrawlerRdbmsMetaClassService_listRetrieval() {
		assertNotNull(schemaCrawlerService);
		List<RdbmsMetaClass> theList = schemaCrawlerRdbmsMetaClassService.getMetaClassList();
		Optional<Collection<Table>> theTables =  schemaCrawlerService.getCatalogTables();
		theTables.ifPresent(t -> {
			 assertTrue( t.size() == theList.size());
		});
		assertTrue (theList.size()>0);
		return theList;
	}
	
}
