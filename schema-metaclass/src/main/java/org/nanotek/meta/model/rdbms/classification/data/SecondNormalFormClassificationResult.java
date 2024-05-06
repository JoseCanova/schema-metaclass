package org.nanotek.meta.model.rdbms.classification.data;

import java.util.List;
import java.util.Map;

public class SecondNormalFormClassificationResult extends ClassificationResult<SecondNormalFormClassificationResult> {

	private static final long serialVersionUID = -737695342685537514L;
	
	private String tableName;
	
	private List<TableIndexResult> indexes;
	
	private Map<String , ?> columnEvaluationIndex;
	
	public SecondNormalFormClassificationResult(String tableName1 , List<TableIndexResult> indexes1 , Map<String , ?> columnEvaluationIndex1) {
		this.tableName = tableName1;
		this.indexes=indexes1;
		this.columnEvaluationIndex = columnEvaluationIndex1;
	}

	public Map<String, ?> getColumnEvaluationIndex() {
		return columnEvaluationIndex;
	}

	public String getTableName() {
		return tableName;
	}

	public List<TableIndexResult> getIndexes() {
		return indexes;
	}

}
