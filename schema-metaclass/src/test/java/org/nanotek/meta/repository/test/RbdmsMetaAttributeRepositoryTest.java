package org.nanotek.meta.repository.test;

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
		att.setIsId(false);
		att.setSqlType("VARCHAR");
		//TODO:Change Type
		att.setLength("255");
		repository.save(att);
	}
	
}
