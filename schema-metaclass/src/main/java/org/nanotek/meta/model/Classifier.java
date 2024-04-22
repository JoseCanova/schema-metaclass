package org.nanotek.meta.model;

import org.nanotek.TagInterface;
import org.nanotek.Base;

@TagInterface
@FunctionalInterface
public interface Classifier<CI> {
	
	<CR extends Base<?>> CR classify(CI classified);
	
}
