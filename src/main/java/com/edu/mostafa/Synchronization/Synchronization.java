package com.edu.mostafa.Synchronization;

public class Synchronization {
    private static int count = 0;

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
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

    private static synchronized void increment() {
        count++;
    }
}
/*Synchronization mechanism

Implemented using locks/monitors at the JVM level.

A synchronized block/method makes the thread enter a monitor before executing code.

Lock acquisition and release

Even if no other thread is competing, the thread must:

Acquire the lock (check and mark it as owned).

Release the lock after finishing.

These operations involve atomic CPU instructions (like compare-and-swap) that are slower than normal instructions.

Memory barriers

Synchronization inserts memory fences/barriers to ensure visibility of changes between threads.

Prevents CPU and compiler from reordering instructions.

This reduces some performance optimizations.

Kernel involvement (sometimes)

In case of contention, the JVM may need OS-level support (parking/unparking threads, context switches).

Even without contention, the JVM prepares for this possibility, adding extra steps.

Why cost remains with one thread

Lock/unlock overhead is still there.

Memory barriers still execute.

CPU pipelines may stall due to atomic instructions.

When it matters

For simple counters or frequently accessed objects, the overhead can be noticeable.

For heavier work inside the synchronized block, the relative cost becomes less important.

👉 So: Synchronization ensures correctness, but always adds some overhead — even if only one thread modifies the resource.*/