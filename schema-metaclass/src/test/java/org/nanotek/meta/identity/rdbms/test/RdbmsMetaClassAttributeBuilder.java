package org.nanotek.meta.identity.rdbms.test;

import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;

public class RdbmsMetaClassAttributeBuilder {

	public RdbmsMetaClassAttributeBuilder() {
	}

	public static RdbmsMetaClassAttribute buildSimpleAttribute() {
		RdbmsMetaClassAttribute att = new RdbmsMetaClassAttribute();
		att.setClazz("java.lang.String");
		att.setColumnName("column_name");
		att.setFieldName("fieldName");
		return att;
	}
	
}
