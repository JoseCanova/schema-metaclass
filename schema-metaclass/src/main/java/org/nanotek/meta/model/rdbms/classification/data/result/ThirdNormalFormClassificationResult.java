package org.nanotek.meta.model.rdbms.classification.data.result;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.nanotek.meta.model.rdbms.classification.data.TableIndexResult;
import org.nanotek.meta.model.rdbms.classification.data.TableTypeEnum;

public class ThirdNormalFormClassificationResult extends ClassificationResult<ThirdNormalFormClassificationResult>{

	
	protected String tableName;
	
	protected TableIndexResult indexes;
	
	public ThirdNormalFormClassificationResult(String tableName1, TableIndexResult indexes1) {
		super(TableTypeEnum.THIRD_NORMAL_FORM);
		postConstruct(tableName1, indexes1);
	}
	
	protected void postConstruct(String tableName1, TableIndexResult indexes1) {
		this.tableName = tableName1;
		this.indexes=indexes1;
	}

	private static final long serialVersionUID = 5919396676044508419L;
	

	public String getTableName() {
		return tableName;
	}

	public TableIndexResult getIndexes() {
		return indexes;
	}
	
}
