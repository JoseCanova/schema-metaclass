package org.nanotek.meta.model.rdbms.classification.data;

import java.util.Collection;
import java.util.Optional;

import schemacrawler.schema.ForeignKey;

public record TableForeignKeys(Optional<Collection<ForeignKey>> keys) {

	public TableForeignKeys (final Optional<Collection<ForeignKey>> keys) {
		this.keys = keys;
	}
	
}
