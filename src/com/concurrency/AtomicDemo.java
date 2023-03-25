package com.concurrency;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class GroupMeetEvent
{
	private String groupName;
	private List<String> userList = new ArrayList<String>();
	AtomicInteger count = new AtomicInteger();
	int i=0;
	public GroupMeetEvent(String groupName)
	{
		this.groupName=groupName;
	}
	public void addAttendee(String userName)
	{
		userList.add(userName);
		count.incrementAndGet();
		i++;
	}
	public void undoAttendee(String userName)
	{
		if(userList.remove(userName))
		{
			count.decrementAndGet();
			i--;
		}
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public List<String> getUserList() {
		return userList;
	}
	public void setUserList(List<String> userList) {
		this.userList = userList;
	}
	public AtomicInteger getCount() {
		return count;
	}
	public void setCount(AtomicInteger count) {
		this.count = count;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	
}
public class AtomicDemo
{
	
	public static void main(String args[]) throws InterruptedException
	{
		GroupMeetEvent ge = new GroupMeetEvent("Java Group");
		Thread thread= new Thread(new Runnable() 
		{
			@Override
			public void run() {
				ge.addAttendee("Naren");
				ge.addAttendee("Naren1");
				ge.addAttendee("Naren2");
			}
		},"T1");
		Thread thread2= new Thread(new Runnable() 
		{
			@Override
			public void run() {
				ge.addAttendee("Naren3");
				ge.addAttendee("Naren4");
				ge.addAttendee("Naren5");
			}
			
		},"T2");
		Thread thread3= new Thread(new Runnable() 
		{
			@Override
			public void run() {
				ge.undoAttendee("Naren3");
			}
			
		},"T3");
		
		thread.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Attendee-"+ge.getCount()+"-"+ge.getI());
		thread2.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Attendee-"+ge.getCount()+"-"+ge.getI());
		thread3.start();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Attendee-"+ge.getCount()+"-"+ge.getI());
		
	}
	
}
