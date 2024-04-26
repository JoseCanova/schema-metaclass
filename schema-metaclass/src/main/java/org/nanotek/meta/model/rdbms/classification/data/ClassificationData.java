package org.nanotek.meta.model.rdbms.classification.data;

public record ClassificationData (TableKey key , TableColumns tableColumns , TableForeignKeys foreignKeys) {
	
	public ClassificationData ( TableKey key ,  TableColumns tableColumns,  TableForeignKeys foreignKeys) {
		this.key = key; 
		this.tableColumns = tableColumns;
		this.foreignKeys = foreignKeys;
	}
	
}