package com.concurrency;


class VolatileTest implements Runnable
{
	private boolean stopAlert=false;

	public VolatileTest(boolean stopAlert) {
		super();
		this.stopAlert = stopAlert;
	}

	@Override
	public void run() 
	{
		System.out.println(Thread.currentThread()+" Started- Stop-"+stopAlert);
		while(!stopAlert)
		{
			System.out.println(Thread.currentThread()+" Still Running");
		}	
	}

	public boolean isStopAlert() {
		return stopAlert;
	}

	public void setStopAlert(boolean stopAlert) {
		System.out.println(Thread.currentThread()+" Send Alert");
		this.stopAlert = stopAlert;
	}	
	
}
class Counter implements Runnable
{
	static volatile int counter=1;

	public int increaseCounter() {
		return counter++;
	}

	@Override
	public void run() 
	{
		for(int i=0;i<10;i++)
		{
			System.out.println(Thread.currentThread().getName()+"-"+increaseCounter());
		}
	}
}
public class VolatileDemo 
{
	public static void main(String args[]) throws InterruptedException
	{
		Thread thread= new Thread(new Counter(),"TA");
		Thread thread1= new Thread(new Counter(),"TB");
		thread.start();
		thread1.start();
		
		/*
		VolatileTest vt= new VolatileTest(false);
		Thread thread1 = new Thread(vt,"Thread-1");
		Thread thread2 = new Thread(vt,"Thread-2");
		thread1.start();
		thread2.start();
		TimeUnit.SECONDS.sleep(3);
		vt.setStopAlert(true);*/
	}

}
