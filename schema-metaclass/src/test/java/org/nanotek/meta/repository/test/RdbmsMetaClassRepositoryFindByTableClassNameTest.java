package org.nanotek.meta.repository.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.TableClassName;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.rdbms.service.SchemaCrawlerRdbmsMetaClassService;
import org.nanotek.meta.repository.RdbmsMetaClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//TODO: Understand problem and optimize find methods on Mongo repository.
@SpringBootTest
public class RdbmsMetaClassRepositoryFindByTableClassNameTest {

	@Autowired
	RdbmsMetaClassRepository repository;
	
	@Autowired
	SchemaCrawlerRdbmsMetaClassService  schemaCrawlerRdbmsMetaClassService;
	
	@Test
	public void findByTableClassNameTest() {
		assertNotNull(schemaCrawlerRdbmsMetaClassService);	
		TableClassName tableClassName = new TableClassName("areacomment","areacomment");
		//prepare mongo repository
		schemaCrawlerRdbmsMetaClassService.getMetaClassList();
		RdbmsMetaClass areaCommentMetaClass = schemaCrawlerRdbmsMetaClassService.findByTableClassName(tableClassName);
		assertNotNull(areaCommentMetaClass);
	}
	
	
	@BeforeEach
	@AfterEach
	void delete() {
		assertNotNull(repository);	
		repository.deleteAll();
	}
}
