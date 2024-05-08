package org.nanotek.meta.classification;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;

@FunctionalInterface
public interface ClassificationTask<R extends Record> {

  <K extends ClassificationResult<?>> Optional<K> evaluate(R cd);
	
}
