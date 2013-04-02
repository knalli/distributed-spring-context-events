package de.knallisworld.springcontextsync.event.integration;

import de.knallisworld.springcontextsync.event.DistributedEvent;
import de.knallisworld.springcontextsync.event.DistributedEventDTO;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;

/**
 * This transformer provides both ways of transforming an event between the internal pojo and the external dto object.
 */
@Component
public class DistributedEventTransformer {

	private static final String DEFAULT_SOURCE = "DEFAULT_SOURCE";

	@Transformer
	public DistributedEventDTO toDTO(final DistributedEvent event) {
		final DistributedEventDTO dto = new DistributedEventDTO();
		dto.setAction(event.getAction());
		dto.setUserId(event.getUserId());
		dto.setCategory(event.getCategory());
		dto.setApplicationId(event.getApplicationId());
		dto.setTimestamp(event.getTimestamp());
		return dto;
	}

	@Transformer
	public DistributedEvent fromDTO(final DistributedEventDTO dto) {
		final DistributedEvent event = new DistributedEvent(DEFAULT_SOURCE, dto.getAction(), dto.getCategory(), dto.getUserId());
		event.setApplicationId(dto.getApplicationId());
		event.setOriginalTimestamp(dto.getTimestamp());
		return event;
	}

}
