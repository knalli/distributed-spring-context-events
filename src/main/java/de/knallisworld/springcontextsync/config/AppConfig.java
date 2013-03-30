package de.knallisworld.springcontextsync.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages = "de.knallisworld")
@Import({ContextEventConfig.class, IntegrationConfig.class})
@ImportResource("classpath:/spring/base.xml")
public class AppConfig {

}
