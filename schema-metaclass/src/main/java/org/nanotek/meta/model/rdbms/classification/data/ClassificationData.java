package org.nanotek.meta.model.rdbms.classification.data;

public record ClassificationData (Key key , TableColumns tableColumns) {
	
	public ClassificationData (Key key , TableColumns tableColumns) {
		this.key = key; 
		this.tableColumns = tableColumns;
	}
	
}