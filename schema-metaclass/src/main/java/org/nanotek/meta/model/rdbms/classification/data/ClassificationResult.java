package org.nanotek.meta.model.rdbms.classification.data;

import org.nanotek.Base;
import org.nanotek.meta.model.IdBase;

public class ClassificationResult implements Base<ClassificationResult> , IdBase<ClassificationResult , TableTypeEnum> {

	private static final long serialVersionUID = 7377567626660137773L;
	
	private TableTypeEnum id;

	public ClassificationResult() {
	}

	public ClassificationResult(TableTypeEnum id) {
		this.id = id;
	}
	
	@Override
	public TableTypeEnum getId() {
		return id;
	}
	
}
