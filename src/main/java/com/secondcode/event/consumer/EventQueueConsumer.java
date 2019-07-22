
package com.secondcode.event.consumer;

import com.secondcode.event.domain.promotion.ShockingEvent;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;

@Component
public class EventQueueConsumer implements EventConsumer {

    private BlockingQueue<ShockingEvent> queue;
    public EventQueueConsumer(@Qualifier("eventQueue") BlockingQueue<ShockingEvent> queue) {
        this.queue = queue;
    }

    @Override
    public void consume() {
        while (queue.size() != 0) {
            try {
                ShockingEvent event = queue.take();
                System.out.println(event.getId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

