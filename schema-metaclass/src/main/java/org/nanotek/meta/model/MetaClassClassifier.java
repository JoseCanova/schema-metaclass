package org.nanotek.meta.model;

import java.util.Optional;

import org.nanotek.Base;

public class MetaClassClassifier implements Base<MetaClassClassifier> , Classifier<MetaClass<?,?>> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public <CR extends Base<?>> Optional<CR> classify(MetaClass<?, ?> classified) {
		return Base.NULL_VALUE();
	}

}
