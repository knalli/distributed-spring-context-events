package de.knallisworld.springcontextsync.event.listener;

import de.knallisworld.springcontextsync.event.DistributedEvent;

/**
 * An interface for event publishers for the DistributedEvent.
 */
public interface DistributedEventPublisher {

	void publishEvent(final String userId, final String action, final String category);

	void publishEvent(final DistributedEvent event);

}
