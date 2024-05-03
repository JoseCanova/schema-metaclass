package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationDataPair;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.springframework.stereotype.Component;


//TODO: prepare the evaluations of the method for unit test.
@Component
public class SecondNormalFormClassificationTask implements TableClassificationTask<ClassificationData> {

	
	public SecondNormalFormClassificationTask() {
	}

	
	@Override
	public Optional<ClassificationResult<?>> evaluate(ClassificationData cd) {
		return TableClassificationTask.super.evaluate(cd);
	}
	
}
