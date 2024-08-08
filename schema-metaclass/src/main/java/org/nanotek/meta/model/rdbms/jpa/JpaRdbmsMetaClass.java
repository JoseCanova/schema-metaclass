package org.nanotek.meta.model.rdbms.jpa;

import org.nanotek.meta.model.rdbms.RdbmsMetaClass;

import schemacrawler.schema.Table;

public class JpaRdbmsMetaClass extends RdbmsMetaClass {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7408656030036390638L;

	public JpaRdbmsMetaClass() {
	}

	public JpaRdbmsMetaClass(String tableName, String className, Table table) {
		super(tableName, className, table);
	}

}