package org.nanotek.meta.controller;

import org.nanotek.meta.rdbms.service.SchemaCrawlerRdbmsMetaClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

//TODO: implement URL for a list of available metaclasses. http://localhost:4200/api/model_relations/classes
@Component
public class RdbmsMetaClassHandler {

	@Autowired
	SchemaCrawlerRdbmsMetaClassService rdbmsSchemaCrawlerService;
	
	public RdbmsMetaClassHandler() {
	}

	public Mono<ServerResponse> getMetaClassList(ServerRequest request) {
	    return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
	      .body(BodyInserters.fromValue(rdbmsSchemaCrawlerService.getMetaClassList()));
	  }

}
