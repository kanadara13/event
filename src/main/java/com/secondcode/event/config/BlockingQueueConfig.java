package com.secondcode.event.config;

import com.secondcode.event.domain.promotion.ShockingEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class BlockingQueueConfig {

    @Bean(name = "eventQueue")
    public BlockingQueue<ShockingEvent> getQueue(){
        return new LinkedBlockingQueue<>(8000);
    }
}
