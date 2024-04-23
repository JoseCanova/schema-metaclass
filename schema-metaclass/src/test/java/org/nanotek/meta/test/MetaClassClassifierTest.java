package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.nanotek.Base;
import org.nanotek.meta.model.MetaClass;

public class MetaClassClassifierTest {
	
	
	@SuppressWarnings("rawtypes")
	@Test
	void testMetaClassClassifier() {
		Optional<MetaClass> omet = Base.newInstance(MetaClass.class);
		omet.ifPresent(met -> {
			assertNotNull(met.getClassifier());
		});
	}

}
