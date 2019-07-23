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

    @Bean(name = "firstQueue")
    public BlockingQueue<ShockingEvent> getFirstQueue(){
        return new LinkedBlockingQueue<>(8000);
    }

    @Bean(name = "secondQueue")
    public BlockingQueue<ShockingEvent> getSecondQueue(){
        return new LinkedBlockingQueue<>(8000);
    }

    @Bean(name = "thirdQueue")
    public BlockingQueue<ShockingEvent> getThirdQueue(){
        return new LinkedBlockingQueue<>(8000);
    }

    @Bean(name = "fourthQueue")
    public BlockingQueue<ShockingEvent> getFourthQueue(){
        return new LinkedBlockingQueue<>(8000);
    }

    @Bean(name = "fifthQueue")
    public BlockingQueue<ShockingEvent> getFifthQueue(){
        return new LinkedBlockingQueue<>(8000);
    }
}
