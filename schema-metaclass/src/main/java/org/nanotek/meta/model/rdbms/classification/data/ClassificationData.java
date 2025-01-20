package org.nanotek.meta.model.rdbms.classification.data;

public record ClassificationData (SchemaTable schemaTable , TableKey key , TableColumns tableColumns , TableForeignKeys foreignKeys , TableIndexes tableIndexes) {
	
	public ClassificationData ( SchemaTable schemaTable, TableKey key ,  TableColumns tableColumns,  TableForeignKeys foreignKeys , TableIndexes tableIndexes) {
		this.schemaTable=schemaTable;
		this.key = key; 
		this.tableColumns = tableColumns;
		this.foreignKeys = foreignKeys;
		this.tableIndexes = tableIndexes;
	}
	
}