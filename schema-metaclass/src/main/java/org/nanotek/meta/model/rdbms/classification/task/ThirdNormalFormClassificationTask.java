package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;

public class ThirdNormalFormClassificationTask implements TableClassificationTask<ClassificationData>{

	@Override
	public <K extends ClassificationResult<?>> Optional<K> evaluate(ClassificationData cd) {
		return TableClassificationTask.super.evaluate(cd);
	}
}
