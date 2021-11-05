package com.maeinghome.thread.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAsync
@Slf4j
public class ThreadConfig implements AsyncConfigurer {
    @Bean("executorSchedule")
    public Executor ExecutorTyc() {
        return new ScheduleAtFixed();
    }

    private class ScheduleAtFixed implements Executor {

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        ThreadPoolTaskExecutor poolExecutor = new ThreadPoolTaskExecutor();

        @Override
        public void execute(Runnable command) {
            log.info("异步：执行时间{}", new Date());
            log.info(executor.toString());
            executor.scheduleAtFixedRate(command, 0, 60, TimeUnit.SECONDS);
        }
    }
}
