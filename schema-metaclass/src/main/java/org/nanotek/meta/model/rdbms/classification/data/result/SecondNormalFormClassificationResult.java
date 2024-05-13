package org.nanotek.meta.model.rdbms.classification.data.result;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.TableIndexResult;
import org.nanotek.meta.model.rdbms.classification.data.TableTypeEnum;

public class SecondNormalFormClassificationResult extends ClassificationResult<SecondNormalFormClassificationResult> {

	private static final long serialVersionUID = -737695342685537514L;
	
	protected String tableName;
	
	protected TableIndexResult indexes;
	
	public SecondNormalFormClassificationResult(String tableName1 , TableIndexResult indexes1 ) {
		super(TableTypeEnum.SECOND_NORMAL_FORM_TABLE);
		postConstruct( tableName1 ,  indexes1);
	}

	protected void postConstruct(String tableName1 , TableIndexResult indexes1) {
		this.tableName = tableName1;
		this.indexes=indexes1;
	}
	
	public String getTableName() {
		return tableName;
	}

	public TableIndexResult getIndexes() {
		return indexes;
	}

}
