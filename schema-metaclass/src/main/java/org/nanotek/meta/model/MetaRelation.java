package org.nanotek.meta.model;


public class MetaRelation {

	protected MetaClassAttribute from; 
	
	protected MetaClassAttribute to;

	
	
	public MetaRelation(MetaClassAttribute from, MetaClassAttribute to) {
		super();
		this.from = from;
		this.to = to;
	}

	public MetaRelation() {
		super();
	}

	public MetaClassAttribute getFrom() {
		return from;
	}

	public void setFrom(MetaClassAttribute from) {
		this.from = from;
	}

	public MetaClassAttribute getTo() {
		return to;
	}

	public void setTo(MetaClassAttribute to) {
		this.to = to;
	}
	
}
