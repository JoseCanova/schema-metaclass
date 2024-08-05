package org.nanotek.meta.model;

import org.nanotek.meta.model.rdbms.RdbmsMetaClassAttribute;

public class MetaRelation {

	protected RdbmsMetaClassAttribute from; 
	
	protected RdbmsMetaClassAttribute to;

	
	
	public MetaRelation(RdbmsMetaClassAttribute from, RdbmsMetaClassAttribute to) {
		super();
		this.from = from;
		this.to = to;
	}

	public MetaRelation() {
		super();
	}

	public RdbmsMetaClassAttribute getFrom() {
		return from;
	}

	public void setFrom(RdbmsMetaClassAttribute from) {
		this.from = from;
	}

	public RdbmsMetaClassAttribute getTo() {
		return to;
	}

	public void setTo(RdbmsMetaClassAttribute to) {
		this.to = to;
	}
	
}
