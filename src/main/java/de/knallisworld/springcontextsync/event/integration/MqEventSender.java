package de.knallisworld.springcontextsync.event.integration;

import de.knallisworld.springcontextsync.event.DistributedEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.annotation.Gateway;
import org.springframework.stereotype.Service;

@Service("mqEventSender")
@Profile("mq")
public interface MqEventSender {

	/**
	 * This method can be called with an event which will be transported to the corresponding channel specified in the
	 * <code>integration.xml</code>.
	 *
	 * @param event
	 */
	@Gateway
	public void send(final DistributedEvent event);

}
