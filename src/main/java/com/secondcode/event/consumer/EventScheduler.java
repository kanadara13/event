package com.secondcode.event.consumer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventScheduler {

    final int cycle = 500;

    private EventQueueConsumer eventQueueConsumer;

    public EventScheduler(EventQueueConsumer eventQueueConsumer) {
        this.eventQueueConsumer = eventQueueConsumer;
    }

    @Scheduled(fixedDelay = (cycle))
    public void run(){
        eventQueueConsumer.consume();
    }
}
