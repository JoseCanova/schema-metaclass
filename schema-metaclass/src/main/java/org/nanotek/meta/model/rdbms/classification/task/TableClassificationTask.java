package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.meta.classification.ClassificationTask;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;

public interface TableClassificationTask<R extends Record> extends ClassificationTask<R>{

	@Override
	default Optional<ClassificationResult<?>> evaluate(R cd) {
		return Optional.empty();
	}
}
