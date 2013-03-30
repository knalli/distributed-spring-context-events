package de.knallisworld.springcontextsync;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("application")
public class Application {

	private String id;

	@PostConstruct
	public void initialize() {
		id = "app" + Math.round(1000 * Math.random());
	}

	public String getId() {
		return id;
	}
}
