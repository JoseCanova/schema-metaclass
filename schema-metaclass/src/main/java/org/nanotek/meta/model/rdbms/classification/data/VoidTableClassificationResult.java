package org.nanotek.meta.model.rdbms.classification.data;

public class VoidTableClassificationResult extends ClassificationResult<VoidTableClassificationResult>{

	private static final long serialVersionUID = 6185055245892016421L;
	
	private String tableName;

	public VoidTableClassificationResult(String tableName) {
		super(TableTypeEnum.VOID_TABLE);
		this.tableName= tableName;
	}

	public String getTableName() {
		return tableName;
	}
	
}
