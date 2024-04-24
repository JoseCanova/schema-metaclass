package org.nanotek;

import java.util.Optional;

@FunctionalInterface
public interface Holder<K>{
	Optional<? super  K> on();
}
