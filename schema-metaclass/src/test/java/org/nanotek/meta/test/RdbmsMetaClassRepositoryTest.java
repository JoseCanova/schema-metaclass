package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.nanotek.Base;
import org.nanotek.meta.model.MetaClass;
import org.nanotek.meta.model.rdbms.RdbmsMetaClass;
import org.nanotek.meta.repository.MetaClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RdbmsMetaClassRepositoryTest<K extends RdbmsMetaClass> {

	@Autowired
	MetaClassRepository<RdbmsMetaClass> rep;
	
	@Test
	void testRdbmsMetaClassRepositoryInsertDelete() {
		assertNotNull(rep);
		Optional<RdbmsMetaClass> omet = Base.newInstance(RdbmsMetaClass.class);
		MetaClass<?,?> met = omet.get();
		assertNotNull(met);
		K newMet = rep.save((K)met);
		assertNotNull(newMet.getId());
		assertNotSame("", newMet.getId());
		rep.delete(newMet);
	}
}
