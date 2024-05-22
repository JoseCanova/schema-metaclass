package org.nanotek.meta.integration;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.springframework.messaging.support.GenericMessage;

public class CatalogTableMessage extends GenericMessage<ClassificationData> {

	private static final long serialVersionUID = 4101433534816857743L;

	public CatalogTableMessage(ClassificationData payload) {
		super(payload);
	}

}
