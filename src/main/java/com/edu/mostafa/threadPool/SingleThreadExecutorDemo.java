package com.edu.mostafa.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThreadExecutorDemo {
    public static void main(String[] args) {
        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            for (int i = 0; i <= 10; i++) {
                service.execute(new TaskSinglePool(i));
            }
        }

    }
}

class TaskSinglePool implements Runnable {
    int taskId;

    TaskSinglePool(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " is being running : with task id: " + taskId);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
/*
 * use thread pool to have many thread beside the main thread
 * and to make sure that number of thread runs by order of queue
 * thread-1 runs first then thread-2 and so on .... all that while main thread is running
 * */