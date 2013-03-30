package de.knallisworld.springcontextsync.event.direct;

import de.knallisworld.springcontextsync.event.DistributedEvent;
import de.knallisworld.springcontextsync.event.listener.DistributedEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Profile("!mq")
public class NonDistributedEventPublisher implements DistributedEventPublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(NonDistributedEventPublisher.class);

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@PostConstruct
	public void afterInitialized() {
		LOGGER.info("Using NonDistributedEventPublisher.");
	}

	@Override
	public void publishEvent(final String userId, final String action, final String category) {
		final DistributedEvent event = new DistributedEvent(this, action, category, userId);
		publishEvent(event);
	}

	/**
	 * Delegate the event right into Spring's event publisher.
	 *
	 * @param event
	 */
	@Override
	public void publishEvent(DistributedEvent event) {

		LOGGER.debug("Publishing...");

		eventPublisher.publishEvent(event);
	}
}
