package com.secondcode.event.producer;


import com.secondcode.event.domain.promotion.ShockingEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;

@Component
public class EventQueueBalancer {

    private BlockingQueue<ShockingEvent> queue1;
    private BlockingQueue<ShockingEvent> queue2;
    private BlockingQueue<ShockingEvent> queue3;
    private BlockingQueue<ShockingEvent> queue4;
    private BlockingQueue<ShockingEvent> queue5;

    @Autowired
    public EventQueueBalancer(@Qualifier("firstQueue") BlockingQueue<ShockingEvent> queue1
                            , @Qualifier("secondQueue") BlockingQueue<ShockingEvent> queue2
                            , @Qualifier("thirdQueue") BlockingQueue<ShockingEvent> queue3
                            , @Qualifier("fourthQueue") BlockingQueue<ShockingEvent> queue4
                            , @Qualifier("fifthQueue") BlockingQueue<ShockingEvent> queue5) {
        this.queue1 = queue1;
        this.queue2 = queue2;
        this.queue3 = queue3;
        this.queue4 = queue4;
        this.queue5 = queue5;
    }


    public BlockingQueue<ShockingEvent> getProperQueue(){
        List<BlockingQueue<ShockingEvent>> queues = Arrays.asList(queue1, queue2, queue3, queue4, queue5);
        return queues.stream().min(Comparator.comparing(BlockingQueue::size)).get();
    }
}
