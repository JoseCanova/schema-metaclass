package org.nanotek.meta.model;

public class MetaClassRelation<R extends MetaClassRelation<R,K>, K extends MetaClass<K,?>> {
	
	protected K metaClass;
	
	public MetaClassRelation() {
		super();
	}

	public MetaClassRelation(K metaClass) {
		super();
		this.metaClass = metaClass;
	} 

}
