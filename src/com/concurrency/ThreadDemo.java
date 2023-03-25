
package com.concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadDemo 
{
	public static void main(String args[]) throws InterruptedException
	{
		Thread thread1 = new Thread(new MyThread(),"Thread-1");
		Thread thread2 = new Thread(new MyThread(),"Thread-2");
		System.out.println("Current Thread..."+Thread.currentThread().getName());
	
		thread1.setPriority(Thread.MAX_PRIORITY);
		thread2.setPriority(Thread.MIN_PRIORITY);
		
		thread1.start();
		thread2.start();
		thread2.yield();
		
	}
}
class MyThread implements Runnable
{
	@SuppressWarnings("static-access")
	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<10;i++)
		{
			System.out.println("Current Thread..."+Thread.currentThread().getName());
		}
	}
}