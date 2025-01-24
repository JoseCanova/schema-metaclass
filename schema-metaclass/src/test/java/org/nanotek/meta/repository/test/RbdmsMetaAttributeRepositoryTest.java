package org.nanotek.meta.repository.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;
import org.nanotek.meta.repository.RbdmsMetaAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RbdmsMetaAttributeRepositoryTest {

	
	@Autowired
	RbdmsMetaAttributeRepository repository;
	
	@Test
	void testRdbmsMetaAttributePersistence() {
		
		var att = new RdbmsMetaClassAttribute();
		
		att.setClazz("ClassName");
		att.setPartOfId(false);
		att.setSqlType("VARCHAR");
		//TODO:Change Type
		att.setLength("255");
		var saveAtt = repository.save(att);
		assertNotNull(saveAtt.getId());
		repository.delete(saveAtt);
		Optional<?> notFound = repository.findById(saveAtt.getId());
		assertTrue (notFound.isEmpty());
	}
	
}
