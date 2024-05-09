package org.nanotek.meta.model.rdbms.classification.data;

public class SecondNormalFormClassificationResult extends ClassificationResult<SecondNormalFormClassificationResult> {

	private static final long serialVersionUID = -737695342685537514L;
	
	private String tableName;
	
	private TableIndexResult indexes;
	
	public SecondNormalFormClassificationResult(String tableName1 , TableIndexResult indexes1 ) {
		this.tableName = tableName1;
		this.indexes=indexes1;
		this.id = TableTypeEnum.SECOND_NORMAL_FORM_TABLE;
	}

	public String getTableName() {
		return tableName;
	}

	public TableIndexResult getIndexes() {
		return indexes;
	}

}
