package org.nanotek.meta.model;

/**
 * Simple immutable record to process list of metaclasses reducing it for table/class names.
 */
public record TableClassName(String tableName , String className) {
	
	public TableClassName(String tableName , String className) {
		this.tableName = tableName; 
		this.className = className;
	}
	
	public String tableName() {
		return this.tableName;
	} 
	
	public String className() {
		return this.className;
	}
	
}
