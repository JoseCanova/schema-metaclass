package org.nanotek.meta.model.rdbms.jpa;

import org.nanotek.meta.model.rdbms.orm.OrmRdbmsMetaClass;

import schemacrawler.schema.Table;

public class JpaRdbmsMetaClass extends OrmRdbmsMetaClass {

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
