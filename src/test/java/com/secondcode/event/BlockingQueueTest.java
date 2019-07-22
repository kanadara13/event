package com.secondcode.event;

import com.secondcode.event.domain.promotion.ShockingEvent;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import static org.junit.Assert.*;

public class BlockingQueueTest {

    @Test
    public void createLinkedBlockingQueueWithCapacity() {
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

    @Test
    public void getOneEventAndIsNotNull(){
        try {
            BlockingQueue<ShockingEvent> q = getFullQueue();
            ShockingEvent e = q.take();
            assertTrue(e.getId() != 0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalStateException.class)
    public void expectedIllegalStateExceptionWhenQisFullAndAddOne(){
        getFullQueue().add(new ShockingEvent(15135135));
    }

    @Test
    public void notThrowExceptionWhenQisFullAndOfferOne(){
        getFullQueue().offer(new ShockingEvent(15135135));
    }

    @Test
    public void notThrowExceptionWhenQisFullAndOfferOneWithTimeLimit() throws InterruptedException {
        assertFalse(getFullQueue().offer(new ShockingEvent(15135135), 5000L, TimeUnit.MILLISECONDS));
    }

    @Test(expected = TimeoutException.class)
    public void waitWhenQisFullAndPutOne() throws TimeoutException, ExecutionException, InterruptedException {
        CompletableFuture.runAsync(()-> {
            try {
                getFullQueue().put(new ShockingEvent(15135135));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).get(2000L,TimeUnit.MILLISECONDS);
    }



    private BlockingQueue<ShockingEvent> getFullQueue() {
        int capacity = 1_500;
        BlockingQueue<ShockingEvent> q = new LinkedBlockingQueue(capacity);
        q.addAll(getEvents(capacity));
        return q;
    }


    private List<ShockingEvent> getEvents(int size) {
        List<ShockingEvent> events = new ArrayList<>(size);
        for (int i=1; i <= size; i++ ){
            events.add(new ShockingEvent(i));
        }
        return events;
    }


}
