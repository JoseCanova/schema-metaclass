package org.nanotek.meta.model;

import java.io.Serializable;

import org.nanotek.Base;
import org.springframework.data.annotation.Id;

public class MetaBase<K extends MetaBase<K,ID>,ID extends Serializable> implements Base<K> , IdBase<K,ID>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1994647285984999840L;
	
	@Id
	ID id;

	@Override
	public ID getId() {
		return id;
	}
	
	
}