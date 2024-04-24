package org.nanotek.meta.model.rdbms.classification.data;

import java.util.Optional;

import org.nanotek.Holder;

public class ClassificationResultHolder implements  Holder<ClassificationData>{

	@Override
	public Optional<? super ClassificationData> on() {
		return Optional.empty();
	}

}
