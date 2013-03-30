package de.knallisworld.springcontextsync.event;

import org.springframework.context.ApplicationEvent;

public class DistributedEvent extends ApplicationEvent {

	private String userId;

	private String action;

	private String category;

	private String applicationId;

	private long originalTimestamp;

	public DistributedEvent(Object source, String action, String category, String userId) {
		super(source);
		this.action = action;
		this.category = category;
		this.userId = userId;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getAction() {
		return action;
	}

	public String getCategory() {
		return category;
	}

	public String getUserId() {
		return userId;
	}

	public long getOriginalTimestamp() {
		return originalTimestamp;
	}

	public void setOriginalTimestamp(long originalTimestamp) {
		this.originalTimestamp = originalTimestamp;
	}
}
