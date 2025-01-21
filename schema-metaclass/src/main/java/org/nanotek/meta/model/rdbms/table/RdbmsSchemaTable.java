package org.nanotek.meta.model.rdbms.table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import schemacrawler.schema.PrimaryKey;
import schemacrawler.schema.Table;

public class RdbmsSchemaTable extends RdbmsTable<Table> {

	@JsonIgnore
	protected Table tableRepresentation;

	public RdbmsSchemaTable (Table theTable) {
		this.tableRepresentation = theTable;
	}
	
	public Table getSchemaTable() {
		return tableRepresentation;
	}
	
	public String getFullName() {
		return tableRepresentation.getFullName();
	}

	public String getName() {
		return tableRepresentation.getName();
	}

	public PrimaryKey getPrimaryKey() {
		return tableRepresentation.getPrimaryKey();
	}
	
}
