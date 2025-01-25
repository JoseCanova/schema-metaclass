package org.nanotek.meta.identity.rdbms.test;

import org.instancio.Instancio;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;

import schemacrawler.schema.Table;


//TODO: generate a more detailed class set to provide a full set of tests for the underlying model.
public class RdbmsMetaClassBuilder {

	public RdbmsMetaClassBuilder() {
	}

	
	public static RdbmsMetaClass buildSimpleRdbmsMetaClass() {
		
		Table schemaTable = new SchemaCrawlerSimpleTable();
		return
				new RdbmsMetaClass("Table_Name" , "Class_Name", schemaTable);
	}
	
}
