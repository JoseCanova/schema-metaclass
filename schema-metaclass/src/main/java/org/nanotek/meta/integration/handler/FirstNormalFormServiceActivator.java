package org.nanotek.meta.integration.handler;

import java.util.Optional;

import org.nanotek.meta.model.rdbms.classification.data.ClassificationData;
import org.nanotek.meta.model.rdbms.classification.data.ClassificationDataPair;
import org.nanotek.meta.model.rdbms.classification.task.FirstNormalFormClassificationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class FirstNormalFormServiceActivator {

	@Autowired 
	private FirstNormalFormClassificationTask task; 
	
	public FirstNormalFormServiceActivator() {
	}

	
	@ServiceActivator(inputChannel = "firstNormalFormMessageChannel")
	public void firstNormalFormServiceActivator(Message<?> message) {
		Object oPayload = message.getPayload();
		Optional<?> optPayload = Optional.class.cast(oPayload);
		optPayload
		.map(obj -> )
		new ClassificationData()
		new ClassificationDataPair(null)
	}
}
