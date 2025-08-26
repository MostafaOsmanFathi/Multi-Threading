package com.edu.mostafa.baicTrheading;

public class ThreadPriority {
    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("UserThread Started executing " + Thread.currentThread().getName());
            }
        });
        System.out.println("thread " + t1.getName() + " Priorty:" + t1.getPriority());
        t1.setPriority(Thread.MAX_PRIORITY);
        System.out.println("thread " + t1.getName() + " Priorty:" + t1.getPriority());

        t1.start();
    }

}
