package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.meta.classification.ClassificationTask;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;

public interface TableClassificationTask<R extends Record , K extends ClassificationResult<?>> extends ClassificationTask<R , K>{

	@Override
	default  Optional<K> evaluate(R cd) {
		return Optional.empty();
	}
}
