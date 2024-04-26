package org.nanotek.meta.model.rdbms.classification.data;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.Id;

public class ClassificationResult implements Base<ClassificationResult> , Id<Optional<?>> {

	private static final long serialVersionUID = 7377567626660137773L;
	
	private Optional<?> id;

	public ClassificationResult() {
	}

	@Override
	public Optional<?> getId() {
		return id;
	}
	
}
