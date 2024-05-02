package org.nanotek.meta.classification;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;

@FunctionalInterface
public interface ClassificationTask<K extends Record> {

  Optional<ClassificationResult<?>> evaluate(K cd);
	
}
