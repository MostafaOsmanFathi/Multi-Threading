package com.edu.mostafa.Synchronization;

import java.util.LinkedList;
import java.util.List;

/*
 * consume and produce sync on Different threads
 * working at same time produce while consuming
 * */
public class ProducerConsumerCoordinator {
    public static void main(String[] args) {
        WorkerCoordinator worker = new WorkerCoordinator(5, 0);
        Thread producer = new Thread(
                () -> {
                    try {
                        worker.produce();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        Thread consumer = new Thread(
                () -> {
                    try {
                        worker.consume();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        producer.start();
        consumer.start();
    }
}

class WorkerCoordinator {
    private int sequnce = 0;
    private final int top;
    private final int bottom;
    private final List<Integer> container;
    private final Object lock = new Object();

    public WorkerCoordinator(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
        container = new LinkedList<>();
    }

    public void produce() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                if (container.size() == top) {
                    System.out.println("Container is full ...");
                    lock.wait();
                }
                lock.notify();
            }
            System.out.println(sequnce + " added to container");
            container.add(sequnce++);
            Thread.sleep(500);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                while (container.size() == bottom) {
                    System.out.println("Container doesn't have enough elements ...");
                    lock.wait();
                }
            }

            System.out.println(container.removeFirst() + " consumed from container");
            Thread.sleep(500);
        }

    }

}