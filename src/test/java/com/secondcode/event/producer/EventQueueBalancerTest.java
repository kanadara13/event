package com.secondcode.event.producer;

import com.secondcode.event.domain.promotion.ShockingEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;

public class EventQueueBalancerTest {

    private EventQueueBalancer balancer;
    BlockingQueue<ShockingEvent> queue1 = new LinkedBlockingQueue<>(500);
    BlockingQueue<ShockingEvent> queue2 = new LinkedBlockingQueue<>(500);
    BlockingQueue<ShockingEvent> queue3 = new LinkedBlockingQueue<>(500);
    BlockingQueue<ShockingEvent> queue4 = new LinkedBlockingQueue<>(500);
    BlockingQueue<ShockingEvent> queue5 = new LinkedBlockingQueue<>(500);

    @Before
    public void setUp() throws Exception {
        balancer = new EventQueueBalancer(queue1, queue2, queue3, queue4, queue5);
    }

    @Test
    public void getMostFreelyQueueIsQueue5() {
        queue1.add(new ShockingEvent(1));
        queue2.add(new ShockingEvent(1));
        queue3.add(new ShockingEvent(1));
        queue4.add(new ShockingEvent(1));
        BlockingQueue<ShockingEvent> selectedQueue = balancer.getProperQueue();
        assertTrue(selectedQueue.toString().equals(queue5.toString()));
        assertTrue(queue5.remainingCapacity() == 500);
    }
}