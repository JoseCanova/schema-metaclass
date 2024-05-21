package org.nanotek.meta.config;

import org.nanotek.meta.rdbms.service.SchemaCrawlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.util.CompoundTrigger;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.scheduling.support.PeriodicTrigger;

@SpringBootConfiguration
public class MetaClassSpringIntegrationConfiguration {

	
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
	public MessageChannel thirdNormalFormMessageChanne() {
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
	

	
	public IntegrationFlow dataModelIntegrationFlow(@Autowired SchemaCrawlerService schemaCrawlerService) {
		return IntegrationFlow.from(() -> new GenericMessage<>(schemaCrawlerService.getCatalogTables()) {} 
											, e -> e.poller(p -> p.fixedRate(5000))).get();
	}
	
}
