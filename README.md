Distributed Spring Context Events
=================================

Demonstration of event object syncing between multiple Spring contexts via RabbitMQ (Proof-Of-Concept). All events were be dispatched with Spring's ApplicationEventPublisher.

# How it works
1. Each `DistributedEvent` will be transformed in a serializable DTO (`DistributedEventDTO`) pojo and sent to a RabbitMQ exchange fanout. 
2. All running contexts have themselfes registered and bounded a temporarily queue to that exchange fanout. 
3. Using this fanout, all bounded queues will get a message which is a DTO of the `DistributedEvent` object.
4. All contexts get this message, transformed it back to an event and publish them via Spring's `ApplicationEventPublisher`.

This means that the context will receive its "own" event via RabbitMQ.

# How to use
Start the application with the `Main.java` found in the base package `de.knallisworld.springcontextsync`. You can start multiple instances in parallel.

# Configuration / Play with it
The `App.java` in the base package defines statically which profiles are enabled: `mq` and `rabbit`. Follow the inlined documentation to change this.

Each instance creates its own unique id found in `Application.java` (also base package).

The configuration of the Spring Integration can be found in `resources/spring/integration.xml`. Any configuration keys used for the RabbitMQ connection are stored in the `resources/application.properties`. The default is `guest/guest` just like the RabbitMQ default are.

# License
Copyright 2013 by Jan Philipp. Licensed under MIT.


[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/knalli/distributed-spring-context-events/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

