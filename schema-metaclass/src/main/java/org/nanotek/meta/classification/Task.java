package org.nanotek.meta.classification;

import java.util.Optional;

@FunctionalInterface
public interface Task<R,K> {

	   Optional<K> evaluate(R cd);
	  
}
