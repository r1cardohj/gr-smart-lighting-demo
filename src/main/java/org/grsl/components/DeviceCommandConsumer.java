package org.grsl.components;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DeviceCommandConsumer {
    private final DeviceJobsQueue deviceCommandQueue;

    public DeviceCommandConsumer(DeviceJobsQueue deviceCommandQueue) {
        this.deviceCommandQueue = deviceCommandQueue;
    }
    @PostConstruct
    public void run() {
        Thread t = new Thread(() -> {
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    Runnable job = deviceCommandQueue.take();
                    job.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        t.start();
    }
}
