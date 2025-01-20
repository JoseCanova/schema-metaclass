package org.nanotek.meta.model;

import java.io.Serializable;

import org.nanotek.Base;
import org.nanotek.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface IdBase<K extends Base<K>,ID extends Serializable> extends Base<K> , Id<ID> {
	
	static <T extends S , S extends IdBase<T,?>>  S prepareBeanInstance(Class<S> clazz) {
		return Base.newInstance(clazz).get();
	}
	
	@JsonIgnore
	default IdBase<K,ID> getIdBase(){
		return this;
	}
}
