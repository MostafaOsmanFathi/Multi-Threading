package com.edu.mostafa.baicTrheading;

/*
 * Main thread has the highest Priority
 * note: that the main thread ended while the other thread are not
 * That's because of the main threat has the highest priority
 * */
public class ThreadCreating {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Main thread started");

        //Different syntax for writing thread

        Thread thread1 = new Thread(new Thread1());
        Thread thread2 = new Thread(new Thread2());
        Thread thread3 = new Thread(new Thread3());
        Thread thread4 = new Thread(Thread4::runTask);
        Thread thread0 = new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Thread 0 started");
                        for (int i = 1; i <= 5; i++) {
                            System.out.println("Thread 0 || i = " + i);
                        }
                        System.out.println("Thread 0 finished executing");
                    }
                }
        );


        thread0.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        System.out.println("Main thread Ended");
    }
}

class Thread1 implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread 1 started");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread 1 || i = " + i);
        }
        System.out.println("Thread 1 finished executing");
    }
}

class Thread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("Thread 2 started");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread 2 || i = " + i);
        }
        System.out.println("Thread 2 finished executing");
    }
}

class Thread3 extends Thread {
    @Override
    public void run() {
        System.out.println("Thread 3 started");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread 3 || i = " + i);
        }
        System.out.println("Thread 3 finished executing");
    }
}

class Thread4 {
    public static void runTask() {
        System.out.println("Thread 4 started");
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread 4 || i = " + i);
        }
        System.out.println("Thread 4 finished executing");
    }
}