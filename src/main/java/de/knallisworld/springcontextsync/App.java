package de.knallisworld.springcontextsync;

import de.knallisworld.springcontextsync.config.AppConfig;
import de.knallisworld.springcontextsync.event.Worker;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.util.StopWatch;

import java.util.Properties;

public class App {

	public static final String MESSAGE_ON_STARTUP = "Starting \"{}\" ({})...";

	public static final String MESSAGE_ON_SHUTDOWN = "Backend will now shutdown, at {}...";

	public static final String MESSAGE_AFTER_STARTUP = "Backend startup completed at {} and has taken {} seconds.";

	private static Logger LOGGER = LoggerFactory.getLogger(App.class);

	private AnnotationConfigApplicationContext context;

	private Properties props;

	private String versionAsString;

	private String productName;

	// yyyy-MM-dd'T'HH:mm:ssZZ
	private DateTimeFormatter isoFormatter;

	// Output
	private DateTimeFormatter targetFormatter;

	public App(final String[] args) {
		this.productName = "Spring Context " + Math.round(Math.random() * 1000);
	}

	public void start() throws InterruptedException {
		LOGGER.info(MESSAGE_ON_STARTUP, getProductName(), getVersionAsString());
		final StopWatch watch = new StopWatch("Context Startup");

		watch.start();
		context = new AnnotationConfigApplicationContext();
		context.register(AppConfig.class);

		// Enable MQ support. If not set, the NonDistributedEventPublisher will be used. RabbitMQ is then obsolete.
		context.getEnvironment().addActiveProfile("mq");

		// Enable RabbitMQ setup. However, w/o the context setup will be errored.
		context.getEnvironment().addActiveProfile("rabbit");

		// Debugging for Spring Integration.
		//context.getEnvironment().addActiveProfile("debug");

		context.setDisplayName("Spring Context Syncer");
		context.addApplicationListener(new ApplicationListener<ContextClosedEvent>() {
			@Override
			public void onApplicationEvent(final ContextClosedEvent event) {
				LOGGER.info(MESSAGE_ON_SHUTDOWN, DateTime.now().toString(isoFormatter));
			}
		});
		watch.stop();

		LOGGER.info(MESSAGE_AFTER_STARTUP, DateTime.now().toString(isoFormatter), watch.getTotalTimeSeconds());

		context.registerShutdownHook();

		context.refresh();
		context.start();

		Thread.sleep(120000);
	}

	private String getVersionAsString() {
		return "1.0.0";
	}

	private String getProductName() {
		return productName;
	}
}
