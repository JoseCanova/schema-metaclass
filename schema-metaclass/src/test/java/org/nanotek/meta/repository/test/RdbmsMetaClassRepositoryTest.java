package org.nanotek.meta.repository.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.rdbms.service.SchemaCrawlerRdbmsMetaClassService;
import org.nanotek.meta.repository.RdbmsMetaClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RdbmsMetaClassRepositoryTest {

	@Autowired
	SchemaCrawlerRdbmsMetaClassService schemaCrawlerRdbmsMetaClassService;
	
	@Autowired
	RdbmsMetaClassRepository  metaClassRepository;
	
	public RdbmsMetaClassRepositoryTest() {
	}
	
	
	@Test
	public void testRdbmsMetaClassMetaClassRepository() {
		assertNotNull(metaClassRepository);
		Optional<RdbmsMetaClass> rdbmsMetaClass = getAnyRdbmsMetaClass();
		rdbmsMetaClass.ifPresentOrElse(rdbms ->{
			RdbmsMetaClass newMet = metaClassRepository.save(rdbms);
			assertNotNull(newMet);
			String id = newMet.getId();
			Optional<?> nonEmptyExpectedResult = metaClassRepository.findById(id);
			assertTrue(nonEmptyExpectedResult.isPresent());
			metaClassRepository.delete(newMet);
			Optional<?> emptyExpectedResult = metaClassRepository.findById(id);
			assertTrue(emptyExpectedResult.isEmpty());
		}, () -> new RuntimeException("not metaclass present to execute test"));
	}


	private Optional<RdbmsMetaClass> getAnyRdbmsMetaClass() {
		assertNotNull(schemaCrawlerRdbmsMetaClassService);
		return schemaCrawlerRdbmsMetaClassService.getMetaClassList().stream().findAny();
	}

}
