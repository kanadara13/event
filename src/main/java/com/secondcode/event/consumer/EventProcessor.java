package com.secondcode.event.consumer;

import com.secondcode.event.domain.promotion.ShockingEvent;
import org.springframework.stereotype.Component;

@Component
public class EventProcessor {

    public void process(ShockingEvent event) {
        //do nothing
    }
}
