package com.secondcode.event.producer;

import com.secondcode.event.domain.promotion.ShockingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class EventProducer {

    private EventQueueBalancer balancer;

    @Autowired
    public EventProducer(EventQueueBalancer balancer) {
        this.balancer = balancer;
    }

    public boolean produce(ShockingEvent event) {
        BlockingQueue<ShockingEvent> eventQ = balancer.getProperQueue();
        if (null == eventQ) {throw new NullPointerException();}
        return eventQ.add(event);
    }


   /* private BlockingQueue<ShockingEvent> queue;
    final int cycle = 100;

    public EventProducer(@Qualifier("eventQueue") BlockingQueue<ShockingEvent> queue) {
        this.queue = queue;
    }

    public boolean produce(ShockingEvent event) {
        return queue.add(event);
    }

    @Scheduled(fixedDelay = (cycle))
    public void produceScheduled(){
        for (int i=0; i < 100; i++){
            queue.add(new ShockingEvent(System.currentTimeMillis()));
        }
    }*/
}