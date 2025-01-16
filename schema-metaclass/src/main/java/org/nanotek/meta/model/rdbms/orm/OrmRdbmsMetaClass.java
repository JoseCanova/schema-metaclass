package org.nanotek.meta.model.rdbms.orm;

import org.nanotek.meta.model.rdbms.RdbmsMetaClass;

import schemacrawler.schema.Table;

//TODO:Analyze again the need of such specialization.
public class OrmRdbmsMetaClass extends RdbmsMetaClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = -345351408363444702L;

	public OrmRdbmsMetaClass() {
	}

	public OrmRdbmsMetaClass(String tableName, String className, Table table) {
		super(tableName, className, table);
	}

}
