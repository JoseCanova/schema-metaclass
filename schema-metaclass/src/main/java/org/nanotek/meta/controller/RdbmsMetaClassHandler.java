package org.nanotek.meta.controller;

import org.nanotek.meta.model.TableClassName;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.rdbms.service.SchemaCrawlerRdbmsMetaClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

//TODO: refactor to a package of specific handler classes
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

	public Mono<ServerResponse> getTableClassNameList(ServerRequest request){
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			      .body(BodyInserters.fromValue(rdbmsSchemaCrawlerService.getTableClassNameList()));
	}
	
	public Mono<ServerResponse> getRdbmsMetaClass(ServerRequest request){
		Mono<TableClassName> tableClassNameMono = request.
													bodyToMono(TableClassName.class); 
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
			      .body(rdbmsSchemaCrawlerService.getRdbmsMetaClass(tableClassNameMono) , RdbmsMetaClass.class);
	}
}
