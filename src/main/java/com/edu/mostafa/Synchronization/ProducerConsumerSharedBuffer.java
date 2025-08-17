package com.edu.mostafa.Synchronization;

import java.util.LinkedList;
import java.util.List;
/*
* consume and produce sync on Different threads
* Consume and produce is not working at the same time only one at a time
* */
public class ProducerConsumerSharedBuffer {
    public static void main(String[] args) {
        WorkerCoordinator worker=new WorkerCoordinator(5,0);
        Thread producer = new Thread(
                ()->{
                    try {
                        worker.produce();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
        Thread consumer = new Thread(
                ()->{
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

class Worker {
    private int sequnce = 0;
    private final int top;
    private final int bottom;
    private final List<Integer> container;
    private final Object lock = new Object();

    public Worker(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
        container = new LinkedList<>();
    }

    public void produce() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (container.size() == top) {
                    System.out.println("Container is full ...");
                    lock.wait();
                } else {
                    System.out.println(sequnce + " added to container");
                    container.add(sequnce++);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (container.size() == bottom) {
                    System.out.println("Container doesn't have enough elements ...");
                    lock.notify();
                    lock.wait();
                } else {
                    System.out.println(container.removeFirst() + " consumed from container");
                }
                Thread.sleep(500);
            }
        }
    }

}