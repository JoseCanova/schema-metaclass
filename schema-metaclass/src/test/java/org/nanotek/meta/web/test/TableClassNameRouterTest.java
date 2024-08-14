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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@ExtendWith(SpringExtension.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TableClassNameRouterTest {

	// Spring Boot will create a `WebTestClient` for you,
	// already configure and ready to issue requests against "localhost:RANDOM_PORT"
	@Autowired
	private WebTestClient webTestClient;
	
	@Autowired 
	private Jackson2ObjectMapperBuilder objectMapperBuilder;

	@Test
	public void testHello() {
		assertNotNull(objectMapperBuilder);
		webTestClient
			.mutate()
			.responseTimeout(Duration.ofMillis(60000))
			// Create a GET request to test an endpoint
			.build()
			.get().uri("/table-classname")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			// and use the dedicated DSL to test assertions against the response
			.expectStatus().isOk()
			.expectBody(ArrayNode.class)
			.value(v -> processArrayNode(v));
	}

	private List<TableClassName> processArrayNode(ArrayNode an) {
		ObjectMapper om = objectMapperBuilder.build();
		List<TableClassName> theList = new ArrayList<>();
		an
		.iterator()
		.forEachRemaining(jn -> processNode(om , theList , jn));
		return theList;
	}

	private TableClassName processNode(ObjectMapper om, List<TableClassName> theList, JsonNode jn) {
		TableClassName rmc = om.convertValue(jn, TableClassName.class);
		theList.add(rmc);
		return rmc;
	}
}
