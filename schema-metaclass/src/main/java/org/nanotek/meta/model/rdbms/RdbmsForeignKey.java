package org.nanotek.meta.model.rdbms;

/**
 * Implementation of a foreign key considering that is a imported key,
 * this is the case of simple relationships that are not composed by a 
 * jointable.
 * 
 */
//TODO: implement a builder that construct a RdbmsForeignKey based on a schema.crawler MutableForeignKey
public class RdbmsForeignKey {

	protected RdbmsMetaClass metaClass; 
	
	protected RdbmsMetaClassAttribute metaClassAttribute;
	
	public RdbmsForeignKey() {}

	public RdbmsForeignKey(RdbmsMetaClass metaClass, RdbmsMetaClassAttribute metaClassAttribute) {
		super();
		this.metaClass = metaClass;
		this.metaClassAttribute = metaClassAttribute;
	}

	public RdbmsMetaClass getMetaClass() {
		return metaClass;
	}

	public void setMetaClass(RdbmsMetaClass metaClass) {
		this.metaClass = metaClass;
	}

	public RdbmsMetaClassAttribute getMetaClassAttribute() {
		return metaClassAttribute;
	}

	public void setMetaClassAttribute(RdbmsMetaClassAttribute metaClassAttribute) {
		this.metaClassAttribute = metaClassAttribute;
	}
	
}
