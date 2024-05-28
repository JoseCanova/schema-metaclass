package org.nanotek.meta.integratoin.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@SpringBootTest
public class SchemaMetaClassIntegrationTest {

	@Autowired
	MessageChannel firstChannel;
	
	@Test
	void testFirstChannelServiceActivator() {
		
		assertNotNull(firstChannel);
		firstChannel.send(MessageBuilder.withPayload("").build());

	}
	
}
