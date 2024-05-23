package org.nanotek.meta.classification;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;

public interface ClassificationTask<R extends Record , K extends ClassificationResult<?>> extends Task<R,K>{

  @Override	
  default Optional<K> evaluate(R cd){
	  return Optional.empty();
  }
	
}
