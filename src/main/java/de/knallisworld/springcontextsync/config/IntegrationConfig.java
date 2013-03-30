package de.knallisworld.springcontextsync.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;

@Configuration
@ImportResource("classpath:spring/integration.xml")
@Profile("mq")
public class IntegrationConfig {
}
