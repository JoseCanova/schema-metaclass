package org.nanotek.meta.classification;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;

@FunctionalInterface
public interface ClassificationTask<K extends Record> {

	<CR extends ClassificationResult<CR>> Optional<CR> evaluate(K cd);
	
}
