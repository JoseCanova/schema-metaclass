package org.nanotek.meta.model.rdbms.table;

import schemacrawler.schema.Table;

public class RdbmsSchemaTable extends RdbmsTable<Table> {

	protected Table tableRepresentation;

	public String getFullName() {
		return tableRepresentation.getFullName();
	}

	public String getName() {
		return tableRepresentation.getName();
	}
	
	
	
}
