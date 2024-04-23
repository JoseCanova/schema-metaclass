package org.nanotek.meta.model;

import org.nanotek.TagInterface;

import java.util.Optional;

import org.nanotek.Base;

@TagInterface
@FunctionalInterface
public interface Classifier<CI> {
	
	<CR extends Base<?>> Optional<CR> classify(CI classified);
	
}
