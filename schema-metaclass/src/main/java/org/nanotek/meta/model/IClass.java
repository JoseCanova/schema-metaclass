package org.nanotek.meta.model;

import java.util.List;

import org.nanotek.meta.model.rdbms.MetaClassAttribute;

public interface IClass {

	String getClassName();

	void setClassName(String className);

	List<MetaClassAttribute> getMetaAttributes();

	boolean addMetaAttribute(MetaClassAttribute attr);


}