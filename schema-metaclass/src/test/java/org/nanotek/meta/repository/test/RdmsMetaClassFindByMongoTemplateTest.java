package org.nanotek.meta.repository.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.rdbms.service.SchemaCrawlerRdbmsMetaClassService;
import org.nanotek.meta.repository.RdbmsMetaClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import reactor.core.publisher.Mono;

@SpringBootTest
public class RdmsMetaClassFindByMongoTemplateTest {

	@Autowired
	RdbmsMetaClassRepository repository;
	
	@Autowired
	ReactiveMongoTemplate mongoTemplate;
	
	@Autowired
	SchemaCrawlerRdbmsMetaClassService  schemaCrawlerRdbmsMetaClassService;
	
	@Test
	@Order(1)
	public void testMongoTemplateReactiveQuery(){
		assertNotNull(mongoTemplate);
		Mono<RdbmsMetaClass> monoMetaClass = mongoTemplate.query(RdbmsMetaClass.class)
				.matching(Criteria.where("className").is("area")).one();
		monoMetaClass.doOnNext(f -> testValue(f))
		.defaultIfEmpty(createNullMetaClass());
//		Flux<Person> result = template.query(Person.class)
//		  .matching(query(where("age").lt(50).and("accounts.balance").gt(1000.00d)))
//		  .all();
		
	}


	private RdbmsMetaClass createNullMetaClass() {
		throw new NoSuchElementException();
	}


	private Object testValue(RdbmsMetaClass f) {
		return f;
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
