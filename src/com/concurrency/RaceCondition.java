package com.concurrency;

public class RaceCondition {
	
	public static void main(String args[])
	{
		BankScenario bs= new BankScenario();
		bs.setBalance(100);
		Thread john = new Thread(bs);
		Thread anita = new Thread(bs);	   
		john.setName("John");
		anita.setName("Anita");
		john.start();
		anita.start();	

	}
}
class BankScenario implements Runnable
{
	public int balance;
	@Override
	public void run() {
		System.out.println("Balance Amount in Main Account..."+balance+" by "+Thread.currentThread().getName());
		withdrawnAmount(75);
		if(balance<0)
		{
			System.out.println("Balance Overdrawn from Main Account by "+Thread.currentThread().getName());
		}
	}
	public synchronized void withdrawnAmount(int amount)
	{
		if(balance>amount)
		{
			System.out.println("Balance ["+amount+"] withdrawn by "+Thread.currentThread().getName());
			balance = balance-amount;
			System.out.println("Balance Amount in Main Account..."+balance+" by "+Thread.currentThread().getName());
		}
		else
		{
			System.out.println("Balance Not Sufficient by "+Thread.currentThread().getName());
		}
	}
	public int getBalance() {
		return balance;
	}
	public  void setBalance(int balance) {
		this.balance = balance;
	}
	
}
