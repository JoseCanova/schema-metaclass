package org.nanotek.meta.integration.handler;

import java.util.Optional;

import org.nanotek.meta.integration.CatalogTableMessage;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationResult;
import org.nanotek.meta.model.rdbms.classification.task.TableClassificationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.AbstractMessageHandler;
import org.springframework.messaging.Message;

public class RdbmsTaskMessageHandler<T extends TableClassificationTask<ClassificationData>> extends AbstractMessageHandler {

	
	private T task;

	public RdbmsTaskMessageHandler(@Autowired T task) {
		this.task = task;
	}
	
	@Override
	protected void handleMessageInternal(Message<?> message) {
		CatalogTableMessage theMessage = CatalogTableMessage.class.cast(message);
		Optional<ClassificationResult<?>> oResult = task.evaluate(theMessage.getPayload());
		Optional
			.ofNullable(message.getHeaders().get("classificationResult"))
			.map(cr -> { message.getHeaders().put("classificationResult" , oResult.get());
				return oResult;});
	}

}
