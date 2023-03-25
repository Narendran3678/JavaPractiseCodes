package com.concurrency.threadCopMgmt;

import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import com.java.utils.IOUtils;

public class ReentrantDemo 
{
	private List<Thread> downloaderThreadList = new ArrayList<>();
	private List<Thread> indexerThreadList = new ArrayList<>();
	private Deque<Weblink> queue = new ArrayDeque<Weblink>();
	class Downloader implements Runnable
	{
		private Lock lock;
		private Condition condition;
		private Weblink weblink;
		public Downloader(Weblink weblink,Lock lock,Condition condition)
		{
			this.lock=lock;
			this.condition=condition;
			this.weblink=weblink;
		}
		@Override
		public void run()
		{
			try
			{
				lock.lock();
				InputStream is = IOUtils.httpConnect(weblink.getUrl(),weblink.getId());
				weblink.setHtmlPage(IOUtils.read(is));
				System.out.println(weblink.getId()+"- Downloaded-"+weblink.getHtmlPage().length());
				condition.signal();
				System.out.println(weblink.getId()+"- Downloader Signaled");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				lock.unlock();
			}
		}
	}
	class Indexer implements Runnable
	{
		private Lock lock;
		private Condition condition;
		private Weblink weblink;
		public Indexer(Weblink weblink,Lock lock,Condition condition)
		{
			this.lock=lock;
			this.condition=condition;
			this.weblink=weblink;
		}
		@Override
		public void run() 
		{
			try
			{
				lock.lockInterruptibly();
				String webPage =weblink.getHtmlPage();	
				while(webPage==null)
				{
					condition.await();
					System.out.println(weblink.getId()+"- Awakened-["+weblink.getHtmlPage().substring(0,20)+"]");
				}
				System.out.println("WebPage "+weblink.getId()+" Indexed");	
			} catch (InterruptedException e) {
				System.out.println(weblink.getId()+"- Indexer Interrupted");
				weblink.setStop(true);
				e.printStackTrace();
			}
			finally
			{
				if (!Thread.currentThread().isInterrupted())
					lock.unlock();
			}
		}
	}
	
	public static void main(String arg[])
	{
		ReentrantDemo reentrantDemo = new ReentrantDemo();
		reentrantDemo.add(reentrantDemo.createWeblink(2001, "Wikipedia", "https://en.wikipedia.org/wiki/Google", "https://en.wikipedia.org/wiki/Google"));
		reentrantDemo.add(reentrantDemo.createWeblink(2002, "Tomcat", "https://tomcat.apache.org/", "https://tomcat.apache.org/"));
		reentrantDemo.add(reentrantDemo.createWeblink(2003, "StackOverflow", "https://stackoverflow.com/", "https://stackoverflow.com/"));
		reentrantDemo.go();
	}
	public void go()
	{
		while(queue.size()>0)
		{
			Weblink weblink = queue.remove();
			Lock lock = new ReentrantLock();
			Condition conditon = lock.newCondition();
			Thread downloaderThread = new Thread(new Downloader(weblink,lock,conditon),"Downloader-Thread");
			Thread inxderThread = new Thread(new Indexer(weblink,lock,conditon),"Indexer-Thread");
			
			downloaderThread.start();
			inxderThread.start();
			downloaderThreadList.add(downloaderThread);
			indexerThreadList.add(inxderThread);
		}
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		System.out.println("Timup For Process");
		for(int i=0;i<downloaderThreadList.size();i++)
		{
			Thread downloaderThread = downloaderThreadList.get(i);
			if(downloaderThread.isAlive())
			{
				System.out.println(downloaderThread.getName()+"-Still Alive, Stopping Indexer Thread");
				indexerThreadList.get(i).interrupt();
			}
		}*/
	}
	public void add(Weblink link) {
		queue.add(link);
	}
	public Weblink createWeblink(long id, String title, String url, String host) {
		Weblink weblink = new Weblink();
    	weblink.setId(id);
    	weblink.setTitle(title);
    	weblink.setUrl(url);
    	weblink.setHost(host);
    	return weblink;
    }
	private static class Weblink {
		private long id;
	    private String title;
		private String url;
	    private String host;
	    
	    private volatile boolean stop;
	    public boolean isStop() {
			return stop;
		}
		public void setStop(boolean stop) {
			this.stop = stop;
		}
	    
	    private String htmlPage;
	    
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getHost() {
			return host;
		}
		public void setHost(String host) {
			this.host = host;
		}
		public String getHtmlPage() {
			return htmlPage;
		}
		public void setHtmlPage(String htmlPage) {
			this.htmlPage = htmlPage;
		}
		@Override
		public String toString() {
			return "Weblink [id=" + id + ", title=" + title + ", url=" + url + ", host=" + host + ", stop=" + stop
					+ ", htmlPage=" + htmlPage + "]";
		}		
		
	}
}
