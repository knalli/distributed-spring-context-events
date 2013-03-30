package de.knallisworld.springcontextsync.event.integration;

import de.knallisworld.springcontextsync.Application;
import de.knallisworld.springcontextsync.event.DistributedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.Headers;
import org.springframework.integration.annotation.Payload;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("mqEventReceiver")
@Profile("mq")
public class MqEventReceiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(MqEventReceiver.class);

	@Autowired
	private ApplicationEventPublisher eventPublisher;

	@Autowired
	private Application application;

	/**
	 * This method is bounded in the <code>integration.xml</code>. For each incoming message, this method will be called.
	 * <p/>
	 * The main purpose of this method is to delegate the incoming event to Spring's application publisher.
	 *
	 * @param event
	 * @param headers
	 */
	@ServiceActivator
	public void receiveAndPublish(@Payload final DistributedEvent event, @Headers Map<String, Object> headers) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("appId={} received an event from appid={}", application.getId(), event.getApplicationId());
		}

		eventPublisher.publishEvent(event);
	}

}
