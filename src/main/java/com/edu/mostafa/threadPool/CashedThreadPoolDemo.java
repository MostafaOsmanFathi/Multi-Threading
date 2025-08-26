package com.edu.mostafa.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CashedThreadPoolDemo {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newCachedThreadPool()) {
            for (int i = 1; i <= 1000; i++) {
                service.execute(new TaskCashedThreadPool(i));
            }
        }
    }
}


class TaskCashedThreadPool implements Runnable {
    int taskID;

    TaskCashedThreadPool(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void run() {
        System.out.println("task ID: " + taskID + " is being excuted by thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}