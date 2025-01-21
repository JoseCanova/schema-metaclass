package org.nanotek.meta.model.classification;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.meta.model.Classifier;
import org.nanotek.meta.model.MetaClass;

public class MetaClassClassifier<M extends MetaClass<M,?>> implements Base<M> , Classifier<M> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public <CR extends Base<?>> Optional<CR> classify(M classified) {
		return Base.NULL_VALUE();
	}

}
