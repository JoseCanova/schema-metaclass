package org.nanotek.meta.model.rdbms.classification.task;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.meta.classification.ClassificationTask;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;

public interface TableClassificationTask extends ClassificationTask{

	@Override
	default <CR extends ClassificationResult> Optional<CR> evaluate(ClassificationData cd) {
		return Optional.empty();
	}
}
