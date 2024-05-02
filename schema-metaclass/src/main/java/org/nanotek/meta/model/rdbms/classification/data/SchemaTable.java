package org.nanotek.meta.model.rdbms.classification.data;

import java.util.Optional;

import schemacrawler.schema.Table;

public record SchemaTable (Optional<Table> table){

	public SchemaTable (Optional<Table> table) {
		this.table = table;
	}
	
}
