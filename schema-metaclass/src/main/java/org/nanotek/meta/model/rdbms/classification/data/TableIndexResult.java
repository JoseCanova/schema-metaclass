package org.nanotek.meta.model.rdbms.classification.data;

import java.util.List;

import schemacrawler.schema.Column;

public record TableIndexResult(String tablename, IndexTypeEnum indexType , List<Column> columns) {

	
	public TableIndexResult(String tablename, IndexTypeEnum indexType , List<Column> columns) {
		this.tablename=tablename;
		this.indexType=indexType;
		this.columns=columns;
	}
	
}
