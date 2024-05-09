package org.nanotek.meta.model.rdbms.classification.data;

import java.util.List;

import schemacrawler.schema.Column;
import schemacrawler.schema.Index;

public record TableIndexResult(IndexTypeEnum indexType , List<Result<List<Index>,Column>> columnsIndexResult) {

	public TableIndexResult(IndexTypeEnum indexType , List<Result<List<Index>,Column>> columnsIndexResult) {
		this.indexType=indexType;
		this.columnsIndexResult=columnsIndexResult;
	}
	
}
