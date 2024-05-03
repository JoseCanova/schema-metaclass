package org.nanotek.meta.model.rdbms.classification.data;

import java.util.Collection;
import java.util.Optional;

import schemacrawler.schema.Index;

public record TableIndexes(Optional<Collection<Index>> tableIndexes) {

	public TableIndexes(final Optional<Collection<Index>> tableIndexes) {
		this.tableIndexes= tableIndexes;
	}
	
}
