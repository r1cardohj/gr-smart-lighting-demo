package org.grsl.components;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Slf4j
@Component
public class DeviceJobsQueue {
    BlockingQueue<Runnable> commandQueue = new ArrayBlockingQueue<>(20);

    public void put(Runnable command) throws InterruptedException {
        commandQueue.put(command);
        log.info("Produced: " + command + " Queue depth is " + commandQueue.size());
    }

    public Runnable take() throws InterruptedException {
        Runnable job = commandQueue.take();
        log.info("Consumed: " + job + " Queue depth is " + commandQueue.size());
        return job;
    }
}
