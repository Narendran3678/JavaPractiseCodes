package com.concurrency.Executors;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

class ExecThread implements Runnable
{
	@Override
	public void run() {
		for(int i=0;i<10;i++)
		{
			System.out.println("Current Thread..."+Thread.currentThread().getName());
		}
	}
	
}
class ExecThread1 implements Runnable
{
	@Override
	public void run() {
		for(int i=0;i<10;i++)
		{
			System.out.println("Current Thread..."+Thread.currentThread().getName());
		}
	}
}

public class ExecutorDemo {
	public static void newSingleThreadExecutor()
	{
		Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(new ExecThread());
		executor.execute(new ExecThread());
	}
	public static void newFixedThreadPool()
	{
		Executor executor = Executors.newFixedThreadPool(2);
		executor.execute(new ExecThread());
		executor.execute(new ExecThread());
	}
	public static void main(String args[]){
		newFixedThreadPool();
	}
}
