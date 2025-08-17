package com.edu.mostafa.Synchronization;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private static Object lock = new Object();

    private static final Queue<Integer> bufferQueue = new LinkedList<>();
    private static final int bufferSize = 5;

    private static int count = 0;

    private static PrintWriter out;

    static {
        try {
            out = new PrintWriter(new FileWriter("out.txt"), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Thread producerThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    while (bufferQueue.size() >= bufferSize) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    ++count;

                    out.println("Produced: " + count);
                    bufferQueue.add(count);


                    lock.notify();
                }

            }
        });
        producerThread.setDaemon(true);

        Thread consumerThread = new Thread(() -> {
            while (true) {
                synchronized (lock) {

                    while (bufferQueue.isEmpty()) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }

                    out.println("Consumed: " + bufferQueue.poll());
                    lock.notify();
                }


            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
