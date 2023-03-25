package com.concurrency.threadCopMgmt;

import java.util.concurrent.TimeUnit;

class MyClass implements Runnable {
    public void run()
    {
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName()+"-Child Thread executing-Interrupt Status-"+Thread.interrupted());
  
                // Here current threads goes to sleeping state
                // Another thread gets the chance to execute
                Thread.sleep(1000);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
  
class ThreadInterruptDemo {
    public static void main(String[] args)
            throws InterruptedException
    {
        Thread thread = new Thread(new MyClass());
        Thread thread1 = new Thread(new MyClass());
        
        thread.start();
        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        // main thread calls interrupt() method on
        // child thread
        thread.interrupt();
        thread1.interrupt();
        System.out.println("Main thread execution completes");
        System.out.println("Interrupt Status-"+Thread.interrupted());
    }
}