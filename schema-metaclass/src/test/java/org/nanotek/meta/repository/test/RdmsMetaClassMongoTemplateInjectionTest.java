package org.nanotek.meta.repository.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootTest
public class RdmsMetaClassMongoTemplateInjectionTest {

	
	@Autowired
	MongoTemplate mongoTemplate;
	
	
	@Test
	@Order(1)
	public void testMongoTemplateInjection(){
		assertNotNull(mongoTemplate);
	}

}
