package org.nanotek.meta.model;

import java.lang.ref.SoftReference;

public class MetaClassReference
<R extends MetaClassReference<R,K>, K extends MetaClass<K,?>> 
extends SoftReference<K>{
	
	protected K metaClass;
	
	public MetaClassReference(K metaClass) {
		super(metaClass);
		this.metaClass = metaClass;
	}

}
