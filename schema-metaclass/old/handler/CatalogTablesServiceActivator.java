package org.nanotek.meta.integration.handler;

import java.util.Optional;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class CatalogTablesServiceActivator {

	public CatalogTablesServiceActivator() {
	}

	@ServiceActivator(inputChannel = "voidMessageChannel" , outputChannel = "firstNormalFormMessageChannel")
	public Optional<?> evaluateCatalogTables(Message<?> message) {
		Object objPayload = message.getPayload();
		Optional<?> oPayload =  Optional.class.cast(objPayload);
		return oPayload;
	}
}
