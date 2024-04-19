package org.nanotek.meta.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MetaClassClassifier implements Classifier<MetaClass> , Serializable {

	protected boolean hasPrimaryKey;

	protected List<MetaRelationClass> metaRelationsClasses;
	
	public MetaClassClassifier() {
		super();
		metaRelationsClasses = new ArrayList<MetaRelationClass>();
	}

	public boolean isHasPrimaryKey() {
		return hasPrimaryKey;
	}

	public void setHasPrimaryKey(boolean hasPrimaryKey) {
		this.hasPrimaryKey = hasPrimaryKey;
	}
	
	public void hasPrimaryKey(boolean b) {
		this.hasPrimaryKey = b;
	}

	public List<MetaRelationClass> getMetaRelationsClasses() {
		return metaRelationsClasses;
	}

	public void setMetaRelationsClasses(List<MetaRelationClass> metaRelationsClasses) {
		this.metaRelationsClasses = metaRelationsClasses;
	}
	
	public void addMetaRelationClass(MetaRelationClass mrc) {
		this.metaRelationsClasses.add(mrc);
		
	}


}
