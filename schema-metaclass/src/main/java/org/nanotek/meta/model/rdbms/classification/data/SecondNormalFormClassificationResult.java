package org.nanotek.meta.model.rdbms.classification.data;

import java.util.List;

import schemacrawler.schema.Index;

public class SecondNormalFormClassificationResult extends ClassificationResult<SecondNormalFormClassificationResult> {

	private static final long serialVersionUID = -737695342685537514L;
	
	private String tableName;
	
	private List<Index> indexes;
	
	public SecondNormalFormClassificationResult(String tableName1 , List<Index> indexes1 ) {
		this.tableName = tableName1;
		this.indexes=indexes1;
	}

	public String getTableName() {
		return tableName;
	}

	public List<Index> getIndexes() {
		return indexes;
	}

}
