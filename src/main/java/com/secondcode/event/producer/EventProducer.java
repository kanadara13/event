package com.secondcode.event.producer;

import com.secondcode.event.domain.promotion.ShockingEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class EventProducer {

    private BlockingQueue<ShockingEvent> queue;
    final int cycle = 100;

    public EventProducer(@Qualifier("eventQueue") BlockingQueue<ShockingEvent> queue) {
        this.queue = queue;
    }

    public boolean produce(ShockingEvent event) {
        return queue.add(event);
    }

    @Scheduled(fixedDelay = (cycle))
    public void produceScheduled(){
        queue.add(new ShockingEvent(System.currentTimeMillis()));
        queue.add(new ShockingEvent(System.currentTimeMillis()));
        queue.add(new ShockingEvent(System.currentTimeMillis()));
        queue.add(new ShockingEvent(System.currentTimeMillis()));
    }
}