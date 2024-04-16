package org.nanotek.meta.model;

import java.util.List;

public interface IClass {

	String getClassName();

	void setClassName(String className);

	List<MetaClassAttribute> getMetaAttributes();

	boolean addMetaAttribute(MetaClassAttribute attr);


}