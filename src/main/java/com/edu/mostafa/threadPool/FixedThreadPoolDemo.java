package com.edu.mostafa.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPoolDemo {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newFixedThreadPool(2)) {
            for (int i = 0; i <= 10; i++) {
                service.execute(new TaskCashedThreadPool(i));
            }
        }
    }
}

class TaskFixedPool implements Runnable {
    int taskID;

    TaskFixedPool(int taskID) {
        this.taskID = taskID;
    }

    @Override
    public void run() {
        System.out.println("task ID: " + taskID + " is being excuted by thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
/*
* same as single thread but instead of excuting one by one thread it excute nThreads at same time
* and a soon as one finsh it start excuting the one that it's turn
* */