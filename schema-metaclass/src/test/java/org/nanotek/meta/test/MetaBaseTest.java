package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.nanotek.Base;
import org.nanotek.meta.model.MetaBase;
import org.nanotek.meta.model.MetaClass;

public class MetaBaseTest {

	
	@SuppressWarnings("rawtypes")
	@Test
	void testMetaBaseNewInstance() {
		Optional<MetaBase> meta = Base.newInstance(MetaBase.class);
		assertNotNull(meta.get());
		assertTrue(meta.get().getClass().equals(MetaBase.class));
	}
	
	
	@Test
	void testMetaClassNewInstance() {
		Optional<MetaClass> meta = Base.newInstance(MetaClass.class);
		assertNotNull(meta.get());
		assertTrue(meta.get().getClass().equals(MetaClass.class));
		MetaClass mt = meta.get();
		assertNotNull(mt);
		assertNull(mt.getId());
	}
	
}
