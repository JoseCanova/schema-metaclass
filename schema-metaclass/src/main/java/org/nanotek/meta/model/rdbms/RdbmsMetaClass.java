package org.nanotek.meta.model.rdbms;

import java.util.List;

import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.model.MetaClassAttribute;

import schemacrawler.schema.Table;

//TODO: move attributes relative to rdbms here. 
public class RdbmsMetaClass extends MetaClass {

	public RdbmsMetaClass() {
	}

	public RdbmsMetaClass(String tableName, String className, List<MetaClassAttribute> metaAttributes) {
		super(tableName, className, metaAttributes);
	}

	public RdbmsMetaClass(String tableName, String className, Table table) {
		super(tableName, className, table);
	}

}
