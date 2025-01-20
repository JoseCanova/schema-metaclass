package org.nanotek.meta.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class PkColumn implements Serializable {

	protected String name;

	public PkColumn() {
		super();
	}

	public PkColumn(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
