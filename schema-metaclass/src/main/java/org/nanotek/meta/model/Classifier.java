package org.nanotek.meta.model;

import java.util.Optional;

import org.nanotek.Base;
import org.nanotek.TagInterface;

@TagInterface
@FunctionalInterface
public interface Classifier<CI> {
	
	<CR extends Base<?>> Optional<CR> classify(CI classified);
	
}
