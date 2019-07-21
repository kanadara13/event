package com.secondcode.event.producer;

import com.secondcode.event.config.BlockingQueueConfig;
import com.secondcode.event.domain.promotion.ShockingEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static org.junit.Assert.*;

public class EventProducerTest {

    private EventProducer producer;
    private BlockingQueue<ShockingEvent> queue;

    @Before
    public void setUp() throws Exception {
        queue = new BlockingQueueConfig().getQueue();
        producer = new EventProducer(queue);
    }


    @Test
    public void produceEventOnce(){
        ShockingEvent event = new ShockingEvent(1);
        assertTrue(producer.produce(event));
    }


    @Test
    public void produceEventMultiple(){
        for (int i=0; i<10_000;i++){
            ShockingEvent event = new ShockingEvent((int) Math.random());
            new Thread(String.valueOf(new EventProducer(queue).produce(event))).start();
        }
    }
}