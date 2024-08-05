package org.nanotek.meta.model;

import java.util.List;

import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;

public interface IClass {

	String getClassName();

	void setClassName(String className);

	List<RdbmsMetaClassAttribute> getMetaAttributes();

	boolean addMetaAttribute(RdbmsMetaClassAttribute attr);


}