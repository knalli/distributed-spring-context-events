package de.knallisworld.springcontextsync.event.listener;

import de.knallisworld.springcontextsync.event.DistributedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * This component listens for DistributedEvents in the global context.
 */
@Component
public class DefaultDistributedEventListener implements ApplicationListener<DistributedEvent> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultDistributedEventListener.class);

	@Override
	public void onApplicationEvent(DistributedEvent event) {

		if (LOGGER.isInfoEnabled()) {
			LOGGER.info(String.format("Event! [%s d=%d] appId=%s, action=%s, userId=%s", new Date(event.getOriginalTimestamp()), event.getTimestamp() - event.getOriginalTimestamp(), event.getApplicationId(), event.getAction(), event.getUserId()));
		}

	}

}
