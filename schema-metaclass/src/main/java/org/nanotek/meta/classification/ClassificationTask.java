package org.nanotek.meta.classification;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;

@FunctionalInterface
public interface ClassificationTask {

	<CR extends ClassificationResult> Optional<CR> evaluate(ClassificationData cd);
	
}
