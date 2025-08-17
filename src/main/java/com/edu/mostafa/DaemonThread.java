package com.edu.mostafa;

/*
 * Daemon Threaed is low priorty thread for background tasks
 * */
public class DaemonThread {
    public static void main(String[] args) {
        System.out.println("Main Thread Started executing " + Thread.currentThread().getName());
        Thread daemonThread = new Thread(new DaemonThread1());
        daemonThread.setDaemon(true);


        Thread userThread = new Thread(new UserThread());

        daemonThread.start();
        userThread.start();
    }
}

class DaemonThread1 implements Runnable {
    @Override
    public void run() {
        System.out.println("DaemonThread Started executing Thread Name:" + Thread.currentThread().getName());

        int count = 1;
        while (count < 500) {
            try {
                System.out.println("DaemonThread " + count + " ......");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ++count;
        }

        System.out.println("DaemonThread1 finished executing");
    }
}

class UserThread implements Runnable {
    @Override
    public void run() {
        System.out.println("UserThread Started executing " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("UserThread Finished executing");
    }
}