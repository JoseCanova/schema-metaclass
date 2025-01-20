package org.nanotek.meta.integration;

import java.util.Collection;
import java.util.Optional;

import org.springframework.messaging.support.GenericMessage;

import schemacrawler.schema.Table;

public class CatalogTableMessage extends GenericMessage<Optional<Collection<Table>>>{

	public CatalogTableMessage(Optional<Collection<Table>> payload) {
		super(payload);
	}

}
