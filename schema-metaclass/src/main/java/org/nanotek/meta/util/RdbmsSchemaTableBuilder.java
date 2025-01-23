package org.nanotek.meta.util;

import org.nanotek.meta.model.rdbms.table.RdbmsSchemaTable;

import schemacrawler.schema.Table;

public class RdbmsSchemaTableBuilder {

	public RdbmsSchemaTableBuilder() {
	}
	
	public static RdbmsSchemaTable from(Table table) {
		return new RdbmsSchemaTable(table);
	}

}
