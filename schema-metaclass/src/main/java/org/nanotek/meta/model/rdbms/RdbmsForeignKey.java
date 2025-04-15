package org.nanotek.meta.model.rdbms;

import org.nanotek.meta.model.MetaClassReference;

/**
 * Implementation of a foreign key considering that is a imported key,
 * this is the case of simple relationships that are not composed by a 
 * jointable.
 * 
 */
public class RdbmsForeignKey 
extends MetaClassReference<RdbmsForeignKey,RdbmsMetaClass>{

	protected RdbmsMetaClassAttribute metaClassAttribute;
	
	protected RdbmsMetaClassAttribute referenceMetaClassAttribute;

	protected RdbmsMetaClass referenceMetaClass;
	
	public RdbmsForeignKey(RdbmsMetaClass metaClass, 
			RdbmsMetaClassAttribute metaClassAttribute,
			RdbmsMetaClass referenceMetaClass,
			RdbmsMetaClassAttribute referenceMetaClassAttribute) {
		super(metaClass);
		this.metaClass = metaClass;
		this.metaClassAttribute = metaClassAttribute;
		this.referenceMetaClass=referenceMetaClass;
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

	public RdbmsMetaClass getReferenceMetaClass() {
		return referenceMetaClass;
	}
	
	@Override
	public String toString() {
		return "RdbmsForeignKey [metaClassAttribute=" + metaClassAttribute.getColumnName() + ", referenceMetaClassAttribute="
				+ referenceMetaClassAttribute.getColumnName() + ", metaClass=" + metaClass.getTableName() + "]";
	}

}
