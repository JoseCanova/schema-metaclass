package org.nanotek;

@FunctionalInterface
public interface MutableIdentity<T> {

	void setId(T t);
	
}
