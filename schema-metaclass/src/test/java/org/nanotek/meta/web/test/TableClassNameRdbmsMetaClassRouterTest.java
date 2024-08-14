package org.nanotek.meta.web.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.meta.model.TableClassName;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@ExtendWith(SpringExtension.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TableClassNameRdbmsMetaClassRouterTest {

	// Spring Boot will create a `WebTestClient` for you,
	// already configure and ready to issue requests against "localhost:RANDOM_PORT"
	@Autowired
	private WebTestClient webTestClient;
	
	@Autowired 
	private Jackson2ObjectMapperBuilder objectMapperBuilder;

	/**
	 * TODO: implement that concrete realization of the filter method on table class list.
	 * This test should work with "the model" added on test resources, or any other model that creates 
	 * a single table named areacomment this is what was planned.
	 */
	@Test
	public void testHello() {
		TableClassName requestBody = new TableClassName("areacomment","areacomment");
		assertNotNull(objectMapperBuilder);
		webTestClient
			.mutate()
			.responseTimeout(Duration.ofMillis(60000))
			// Create a GET request to test an endpoint
			.build()
			.post()
			.uri("/rdbms-metaclass")
			.body(BodyInserters.fromValue(requestBody))
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			// and use the dedicated DSL to test assertions against the response
			.expectStatus().isOk()
			.expectBody(Object.class)
			.value(v -> processRdbmsMetaClassObjectNode(v));
//			.value(v -> processArrayNode(v));
	}

	private Object processRdbmsMetaClassObjectNode(Object v) {
		ObjectMapper om = objectMapperBuilder.build();
		RdbmsMetaClass rmc = om.convertValue(v, RdbmsMetaClass.class);
		//TODO: Prepare Object assertions 
		return null;
	}

	private List<RdbmsMetaClass> processArrayNode(ArrayNode an) {
		ObjectMapper om = objectMapperBuilder.build();
		List<RdbmsMetaClass> theList = new ArrayList<>();
		an
		.iterator()
		.forEachRemaining(jn -> processNode(om , theList , jn));
		return theList;
	}

	private RdbmsMetaClass processNode(ObjectMapper om, List<RdbmsMetaClass> theList, JsonNode jn) {
		RdbmsMetaClass rmc = om.convertValue(jn, RdbmsMetaClass.class);
		theList.add(rmc);
		return rmc;
	}
}
