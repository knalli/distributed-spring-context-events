package de.knallisworld.springcontextsync.event.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Component;

@Component
public class MqDistributedEventExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(MqDistributedEventExceptionHandler.class);

	@ServiceActivator
	public void handleException(final Exception e) {
		LOGGER.warn("Event could not be handled correctly.", e);
	}

}
