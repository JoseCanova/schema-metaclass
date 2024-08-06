package org.nanotek.meta.web.test;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.databind.JsonNode;

@ExtendWith(SpringExtension.class)
//  We create a `@SpringBootTest`, starting an actual server on a `RANDOM_PORT`
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RdbmsMetaClassRouterTest {

	// Spring Boot will create a `WebTestClient` for you,
	// already configure and ready to issue requests against "localhost:RANDOM_PORT"
	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void testHello() {
		webTestClient
			.mutate()
			.responseTimeout(Duration.ofMillis(60000))
			// Create a GET request to test an endpoint
			.build()
			.get().uri("/meta-class")
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			// and use the dedicated DSL to test assertions against the response
			.expectStatus().isOk()
			.expectBody(List.class)
			.value(el -> processList(el));
	}

	private List<?> processList(List<?> el) {
		return el;
	}
}
