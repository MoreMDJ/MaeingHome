package com.maeinghome.thread.service.impl;

import com.maeinghome.thread.service.ITaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TaskServiceImpl implements ITaskService {
    @Override
    @Async("executorSchedule")
    public void taskOne() {
        log.info("one:" + Thread.currentThread().getName());
    }

    @Override
    public void taskTwo() {
        log.info("two:" + Thread.currentThread().getName());
    }
}
