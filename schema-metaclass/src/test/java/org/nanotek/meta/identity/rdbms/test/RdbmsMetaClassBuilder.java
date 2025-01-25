package org.nanotek.meta.identity.rdbms.test;

import org.nanotek.meta.model.rdbms.RdbmsMetaClass;

import schemacrawler.schema.Table;

public class RdbmsMetaClassBuilder {

	public RdbmsMetaClassBuilder() {
	}

	
	public static RdbmsMetaClass buildRdbmsMetaClass() {
		
		Table schemaTable = new SchemaCrawlerSimpleTable();
		return
				new RdbmsMetaClass("Table_Name" , "Class_Name", schemaTable);
	}
	
}
