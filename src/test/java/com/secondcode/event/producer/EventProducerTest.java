package com.secondcode.event.producer;

import com.secondcode.event.config.BlockingQueueConfig;
import com.secondcode.event.domain.promotion.ShockingEvent;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventProducerTest {

    private EventProducer producer;
    private EventQueueBalancer mockBalancer;

    @Before
    public void setUp() throws Exception {
        mockBalancer = mock(EventQueueBalancer.class);
        producer = new EventProducer(mockBalancer);
    }

    @Test(expected = NullPointerException.class)
    public void addOneInQueue_ThrowNullPointException(){
        ShockingEvent event = new ShockingEvent(1);
        producer.produce(event);
        verify(mockBalancer).getProperQueue();
    }

    @Test
    public void shouldAddOne(){
        ShockingEvent event = new ShockingEvent(1);
        when(mockBalancer.getProperQueue()).thenReturn(new LinkedBlockingQueue<>());
        producer.produce(event);
        verify(mockBalancer).getProperQueue();
    }


}