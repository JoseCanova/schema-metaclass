package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.nanotek.Base;
import org.nanotek.meta.model.MetaClass;

public class MetaClassUUIDBaseTest {

	
	@Test
	void testMetaClassUUIDBase() {
		Optional<MetaClass> omet = Base.newInstance(MetaClass.class);
		UUID metUUID = Base.withUUID(omet.get().getClass());
		UUID metClassUUID = Base.withUUID(MetaClass.class);
		assertEquals(metUUID, metClassUUID);
		System.out.println(metClassUUID.toString());
		MetaClass cmet = omet.get();
		UUID uid = cmet.withUUID();
		System.out.println(uid.toString());
//		cmet.setClassName("TheClassName");
		Optional<MetaClass> omet1 = Base.newInstance(MetaClass.class);
		MetaClass cmet1 = omet1.get();
		UUID uid3 = cmet1.withUUID();
		System.out.println(uid3.toString());
		assertEquals(uid , uid3);
		cmet.setClassName("TheClassName");
		UUID uid4 = cmet.withUUID();
		assertNotEquals(uid, uid4);
		assertNotEquals(uid3, uid4);
		System.out.print(uid4.toString());
	}
	
	
}
