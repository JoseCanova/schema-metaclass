package org.nanotek.meta.repository.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.TableClassName;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.rdbms.service.SchemaCrawlerRdbmsMetaClassService;
import org.nanotek.meta.repository.RdbmsMetaClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//TODO:Refactor test to load metaclasses into metaclass rdbms repository
//TODO:Refactor test to delete all after each test.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RdbmsMetaClassRepositoryFindByTableClassNameTest {

	@Autowired
	RdbmsMetaClassRepository repository;
	
	@Autowired
	SchemaCrawlerRdbmsMetaClassService  schemaCrawlerRdbmsMetaClassService;
	
	@Test
	public void findByTableClassNameTest() {
		assertNotNull(schemaCrawlerRdbmsMetaClassService);	
		TableClassName tableClassName = new TableClassName("areacomment","areacomment");
		List<RdbmsMetaClass> areaCommentMetaClass = schemaCrawlerRdbmsMetaClassService.findByClassName(tableClassName.className());
		assertNotNull(areaCommentMetaClass);
		assertTrue(areaCommentMetaClass.size()==1);
	}
	
	
	@BeforeEach
	void addAll() {
		assertNotNull(schemaCrawlerRdbmsMetaClassService);	
		schemaCrawlerRdbmsMetaClassService.retrieveMetaClassList();
	}
	@AfterEach
	void delete() {
		assertNotNull(repository);	
		repository.deleteAll();
	}
}
