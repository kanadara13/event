
package com.secondcode.event.consumer;

import com.secondcode.event.domain.promotion.ShockingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;

@Component
public class EventQueueConsumer implements EventConsumer {

    private BlockingQueue<ShockingEvent> queue1;
    private BlockingQueue<ShockingEvent> queue2;
    private BlockingQueue<ShockingEvent> queue3;
    private BlockingQueue<ShockingEvent> queue4;
    private BlockingQueue<ShockingEvent> queue5;
    private EventProcessor processor;

    @Autowired
    public EventQueueConsumer(@Qualifier("firstQueue") BlockingQueue<ShockingEvent> queue1
            , @Qualifier("secondQueue") BlockingQueue<ShockingEvent> queue2
            , @Qualifier("thirdQueue") BlockingQueue<ShockingEvent> queue3
            , @Qualifier("fourthQueue") BlockingQueue<ShockingEvent> queue4
            , @Qualifier("fifthQueue") BlockingQueue<ShockingEvent> queue5, EventProcessor processor) {
        this.queue1 = queue1;
        this.queue2 = queue2;
        this.queue3 = queue3;
        this.queue4 = queue4;
        this.queue5 = queue5;
        this.processor = processor;
    }

    @Override
    public void consume() {
        List<BlockingQueue<ShockingEvent>> queues = Arrays.asList(queue1, queue2, queue3, queue4, queue5);
        queues.stream().map(e->CompletableFuture.runAsync(()-> take(e)));
    }

    private void take(BlockingQueue<ShockingEvent> queue) {
        while (queue.size() != 0) {
            try {
                ShockingEvent event = queue.take();
                processor.process(event);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

