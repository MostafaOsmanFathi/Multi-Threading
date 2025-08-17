package com.edu.mostafa.Synchronization;

public class UnSynchronization {
    private static int count = 0;

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    count++;
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

/*

 * 1. Load
 * 2. Increment
 * 3. Set back the value

 * counter = 0; incrementValue = 1; Setting back the value to counter = 1 < Thread 1
 * counter = 0; incrementValue = 1 (2); < Thread 2

 */