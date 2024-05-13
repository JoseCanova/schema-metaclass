package org.nanotek.meta.model.rdbms.classification.data.result;

import org.nanotek.meta.model.rdbms.classification.data.TableIndexResult;
import org.nanotek.meta.model.rdbms.classification.data.TableTypeEnum;

public class ThirdNormalFormClassificationResult extends SecondNormalFormClassificationResult{

	public ThirdNormalFormClassificationResult(String tableName1, TableIndexResult indexes1) {
		super(tableName1, indexes1);
	}
	
	@Override
	protected void postConstruct(String tableName1, TableIndexResult indexes1) {
		this.tableName = tableName1;
		this.indexes=indexes1;
		this.id = TableTypeEnum.THIRD_NORMAL_FORM;
	}

	private static final long serialVersionUID = 5919396676044508419L;
	
}
