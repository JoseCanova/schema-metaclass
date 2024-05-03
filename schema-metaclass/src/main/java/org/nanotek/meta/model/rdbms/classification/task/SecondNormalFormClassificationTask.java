package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationDataPair;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;

public class SecondNormalFormClassificationTask implements TableClassificationTask<ClassificationDataPair> {

	
	public SecondNormalFormClassificationTask() {
	}

	
	@Override
	public Optional<ClassificationResult<?>> evaluate(ClassificationDataPair cd) {
		return TableClassificationTask.super.evaluate(cd);
	}
	
}
