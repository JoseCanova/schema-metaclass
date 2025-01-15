package org.nanotek.meta.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.util.CompoundTrigger;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;

@SpringBootConfiguration
public class MetaClassSpringIntegrationConfiguration {

	
	@Bean
	public MessageChannel firstChannel() {
		return new DirectChannel();
	}
	
	@Bean 
	public MessageChannel voidMessageChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageChannel firstNormalFormMessageChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageChannel secondNormalFormMessageChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageChannel secondNormalFormChannelResultChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageChannel splitCatalogMessageChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public MessageChannel thirdNormalFormMessageChannel() {
		return new DirectChannel();
	}
	
	@Bean 
	public CronTrigger defaultCronTrigger() {
		return new CronTrigger("0 0 * * * *");
	}
	
	@SuppressWarnings("deprecation")
	@Bean 
	public PeriodicTrigger newPeriodicTrigger() {
		return new PeriodicTrigger(60000);
	}
	
	@Bean 
	public CompoundTrigger compoundTrigger(@Autowired CronTrigger defaultCronTrigger) {
		return new CompoundTrigger(defaultCronTrigger);
	}
	

	@Bean
	public IntegrationFlow dataModelIntegrationFlow() {
		return IntegrationFlow
				.from("initChannel")
				.channel(firstChannel())
				.get();
	}
	
	@Bean
	public IntegrationFlow splitModelIntegrationFlow() {
		return IntegrationFlow
				.from("splitCatalogMessageChannel")
				.split()
				.channel(secondNormalFormMessageChannel())
				.get();
	}
	
	@Bean
	public IntegrationFlow thirdModelIntegrationFlow() {
		return IntegrationFlow
				.from("secondNormalFormChannelResultChannel")
				.filter(Message.class , m -> {
					Object result =  m.getHeaders().get("result");
					Optional<?> csf2 = Optional.class.cast(result);
					return csf2.isPresent();
				})
				.channel(thirdNormalFormMessageChannel())
				.get();
	}
	
}
