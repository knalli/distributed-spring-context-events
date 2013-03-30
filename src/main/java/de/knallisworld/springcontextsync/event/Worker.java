package de.knallisworld.springcontextsync.event;

import de.knallisworld.springcontextsync.event.listener.DistributedEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Worker {

	private static final Logger LOGGER = LoggerFactory.getLogger(Worker.class);

	/**
	 * Depending on the profile "mq", the bean is either NonDistributedEventPublisher or MqDistributedEventPublisher.
	 */
	@Autowired
	private DistributedEventPublisher eventPublisher;

	@Scheduled(fixedDelay = 1000, initialDelay = 10000)
	public void generate() {

		final String userId = UUID.randomUUID().toString();
		final String action = "user.do.anything";

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Creating event: user={}, action={}", userId, action);
		}

		eventPublisher.publishEvent(userId, action, null);
	}

}
