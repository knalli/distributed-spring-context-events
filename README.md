spring-context-event-sync
=========================

Demonstration of event object syncing between multiple Spring contexts via RabbitMQ (Proof-Of-Concept)

# How to use
Start the application with the `Main.java` found in the base package `de.knallisworld.springcontextsync`. You can start multiple instances in parallel.

# Configuration / Play with it
The `App.java` in the base package defines statically which profiles are enabled: `mq` and `rabbit`. Follow the inlined documentation to change this.

Each instance creates its own unique id found in `Application.java` (also base package).

The configuration of the Spring Integration can be found in `resources/spring/integration.xml`. Any configuration keys used for the RabbitMQ connection are stored in the `resources/application.properties`. The default is `guest/guest` just like the RabbitMQ default are.

# License
Copyright 2013 by Jan Philipp. Licensed under MIT.
