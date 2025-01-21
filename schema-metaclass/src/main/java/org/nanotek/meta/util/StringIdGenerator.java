package org.nanotek.meta.util;

import java.util.UUID;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;

public class StringIdGenerator implements IdentifierGenerator, Configurable {

	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) {
		return UUID.randomUUID().toString();
	}

}
