package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationDataPair;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;


//TODO: prepare the evaluations of the method for unit test.
public class SecondNormalFormClassificationTask implements TableClassificationTask<ClassificationDataPair> {

	
	public SecondNormalFormClassificationTask() {
	}

	
	@Override
	public Optional<ClassificationResult<?>> evaluate(ClassificationDataPair cd) {
		return TableClassificationTask.super.evaluate(cd);
	}
	
}
