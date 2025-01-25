package org.nanotek.meta.util;

import org.nanotek.meta.model.rdbms.table.RdbmsSchemaTable;
import java.util.function.*;
import schemacrawler.schema.Table;

public class RdbmsSchemaTableBuilder 
implements Function<Table,RdbmsSchemaTable>{

	private RdbmsSchemaTableBuilder() {
	}
	
	public static RdbmsSchemaTableBuilder on() {
		return new RdbmsSchemaTableBuilder();
	}
	
	public RdbmsSchemaTable apply(Table table) {
		return new RdbmsSchemaTable(table);
	}

}
