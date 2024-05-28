package org.nanotek.meta.integration.handler;

import java.util.Collection;
import java.util.Optional;

import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import schemacrawler.schema.Table;

@Component
public class SchemaCrawlerServiceActivator {

	@Autowired
	SchemaCrawlerService schemaCrawlerService;
	
	public SchemaCrawlerServiceActivator() {
	}
	
	@ServiceActivator(inputChannel = "firstChannel" , outputChannel = "voidMessageChannel")
	public Message<?> handleMessageInternal(Message<?> message) {
		 Optional<Collection<Table>> payload =  schemaCrawlerService.getCatalogTables();
		 return MessageBuilder.withPayload(payload).build();
	}
}
