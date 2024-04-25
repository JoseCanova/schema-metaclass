package org.nanotek.meta.classification;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;

@FunctionalInterface
public interface ClassificationTask {

	<CR extends Base<?>> Optional<CR> evaluate(ClassificationData cd);
	
}
