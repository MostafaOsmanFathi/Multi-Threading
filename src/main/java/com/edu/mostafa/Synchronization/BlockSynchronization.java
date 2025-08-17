package com.edu.mostafa.Synchronization;

public class BlockSynchronization {
    private static int count = 0;
    private static Object lock = new Object();


    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    synchronized (lock) {
                        count++;
                    }
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Count: " + count);
    }

}
