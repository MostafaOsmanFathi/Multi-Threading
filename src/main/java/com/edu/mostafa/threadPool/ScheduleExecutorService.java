package com.edu.mostafa.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorService {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
        service.scheduleAtFixedRate(new ProbeTask(), 1000, 2000, TimeUnit.MILLISECONDS);
        try {
            if (!service.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
                service.shutdownNow();
            }
        } catch (Exception e) {
            service.shutdownNow();
            System.out.println(e.getMessage());
        }
    }
}

class ProbeTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Probing end for task for update ....");
    }
}
