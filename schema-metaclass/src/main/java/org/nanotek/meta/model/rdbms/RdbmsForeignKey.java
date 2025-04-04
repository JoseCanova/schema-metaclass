package org.nanotek.meta.model.rdbms;

import org.nanotek.meta.model.MetaClassRelation;

/**
 * Implementation of a foreign key considering that is a imported key,
 * this is the case of simple relationships that are not composed by a 
 * jointable.
 * 
 */
public class RdbmsForeignKey 
extends MetaClassRelation<RdbmsForeignKey,RdbmsMetaClass>{

	protected RdbmsMetaClassAttribute metaClassAttribute;
	
	protected RdbmsMetaClassAttribute referenceMetaClassAttribute;
	
	public RdbmsForeignKey() {}

	public RdbmsForeignKey(RdbmsMetaClass metaClass, RdbmsMetaClassAttribute metaClassAttribute,
			RdbmsMetaClassAttribute referenceMetaClassAttribute) {
		super(metaClass);
		this.metaClass = metaClass;
		this.metaClassAttribute = metaClassAttribute;
		this.referenceMetaClassAttribute = referenceMetaClassAttribute;
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

	public RdbmsMetaClassAttribute getReferenceMetaClassAttribute() {
		return referenceMetaClassAttribute;
	}

	public void setReferenceMetaClassAttribute(RdbmsMetaClassAttribute referenceMetaClassAttribute) {
		this.referenceMetaClassAttribute = referenceMetaClassAttribute;
	}

	@Override
	public String toString() {
		return "RdbmsForeignKey [metaClassAttribute=" + metaClassAttribute.getColumnName() + ", referenceMetaClassAttribute="
				+ referenceMetaClassAttribute.getColumnName() + ", metaClass=" + metaClass.getTableName() + "]";
	}
	
	
	
}
