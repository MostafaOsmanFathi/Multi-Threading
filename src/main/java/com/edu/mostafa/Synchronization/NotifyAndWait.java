package com.edu.mostafa.Synchronization;

public class NotifyAndWait {
    private static Object lock = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                one();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                two();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        });

        t1.start();
        t2.start();
    }

    public static void one() throws InterruptedException {
        synchronized (lock) {
            System.out.println("method one started ......");
            lock.wait();
            System.out.println("method one ended ......");
        }
    }

    public static void two() throws InterruptedException {
        synchronized (lock) {
            System.out.println("method two started ......");
            lock.notify(); // wakes up one thread connected to lock object
//            lock.notifyAll(); // wakes up all thread connected to lock object
            System.out.println("method two ended ......");
        }
    }
}
