package com.secondcode.event;

import com.secondcode.event.domain.promotion.ShockingEvent;
import org.junit.Test;
import org.junit.runners.model.TestTimedOutException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class BlockingQueueTest {

    @Test
    public void createLinkedBlockingQueueWithCapacity() throws InterruptedException {
        int capacity = 1_500;
        BlockingQueue<ShockingEvent> q = new LinkedBlockingQueue(capacity);
        q.addAll(getEvents(capacity));
        assertTrue("1500개의 queue", q.size() == capacity);
        q.stream().forEach(e->{
            try {
                q.poll(1000L, TimeUnit.MILLISECONDS).getId();
                System.out.println("q.remainingCapacity()" + q.remainingCapacity());
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        assertTrue("is q empty?",q.isEmpty());
    }

    @Test(expected = IllegalStateException.class)
    public void expectedIllegalStateExceptionWhenQisFullAndAddOne(){
        getFullQueue().add(new ShockingEvent(15135135));
    }

    @Test
    public void notThrowExceptionWhenQisFullAndOfferOne(){
        getFullQueue().offer(new ShockingEvent(15135135));
        System.out.println("is wait?");
    }

    @Test
    public void notThrowExceptionWhenQisFullAndOfferOneWithTimeLimit() throws InterruptedException {
        assertFalse(getFullQueue().offer(new ShockingEvent(15135135), 5000L, TimeUnit.MILLISECONDS));
    }

    @Test(timeout = 3000L, expected = InterruptedException.class)
    public void waitWhenQisFullAndPutOne() throws InterruptedException {
       getFullQueue().put(new ShockingEvent(15135135));
    }



    private BlockingQueue<ShockingEvent> getFullQueue() {
        int capacity = 1_500;
        BlockingQueue<ShockingEvent> q = new LinkedBlockingQueue(capacity);
        q.addAll(getEvents(capacity));
        return q;
    }


    private List<ShockingEvent> getEvents(int size) {
        List<ShockingEvent> events = new ArrayList<>(size);
        for (int i=0; i < size; i++ ){
            events.add(new ShockingEvent(i));
        }
        return events;
    }


}
