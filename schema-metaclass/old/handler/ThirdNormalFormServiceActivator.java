package org.nanotek.meta.integration.handler;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ThirdNormalFormServiceActivator {

	@ServiceActivator(inputChannel = "thirdNormalFormMessageChannel")
	public void thirdNormalFormServiceActivator(Message <?> message) {
		System.err.println("Arrive message third");
	}

}
