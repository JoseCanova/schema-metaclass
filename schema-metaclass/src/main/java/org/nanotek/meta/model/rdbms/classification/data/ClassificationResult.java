package org.nanotek.meta.model.rdbms.classification.data;

import org.nanotek.Base;
import org.nanotek.meta.model.IdBase;

public class ClassificationResult<K extends ClassificationResult<K> > implements Base<K> , IdBase<K , TableTypeEnum> {

	private static final long serialVersionUID = 7377567626660137773L;
	
	protected TableTypeEnum id;

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
