package com.secondcode.event.consumer;


import com.secondcode.event.domain.promotion.ShockingEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

@Component
public class EventConsumer {

    final int cycle = 1000;

    private BlockingQueue<ShockingEvent> queue;

    public EventConsumer(@Qualifier("eventQueue") BlockingQueue<ShockingEvent> queue) {
        this.queue = queue;
    }

    @Scheduled(fixedDelay = (cycle))
    public void consume(){
        if (queue.size() == 0) {return;}
        queue.stream().forEach(e->{
            try {
                System.out.println(e.getId());
                queue.poll(cycle, TimeUnit.MILLISECONDS).getId();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        System.out.println("사이즈 :" + queue.size());
        System.out.println("남은 갯수 :" + queue.remainingCapacity());
    }
}
