package com.concurrency.Executors;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.java.utils.IOUtils;

public class ExecutorsNotifyAllIndexer 
{
	private static final long TIME_FRAME = 1000000000L;
	Deque<Weblink> queue = new ArrayDeque<Weblink>();
	ExecutorService downloaderService 	= Executors.newFixedThreadPool(10);
	ExecutorService indexerService 		= Executors.newFixedThreadPool(10);
	private static class DownloaderThread<T extends Weblink> implements Callable<T>
	{
		private T weblink;
		private DownloaderThread(T weblink)
		{
			this.weblink=weblink;
		}
		@Override
		public T call() throws Exception 
		{
			InputStream is = IOUtils.httpConnect(weblink.getUrl());
			weblink.setHtmlPage(IOUtils.read(is));
			System.out.println(weblink.getId()+" - Downloaded");
			return weblink;
		}	
	}
	private static class IndexerThread implements Runnable
	{
		private Weblink weblink;
		private Future<Weblink> future ;
		private IndexerThread(Future<Weblink> future)
		{	
			this.future=future;
		}
	
		@Override
		public void run() {
			try {
				weblink=future.get();
				System.out.println(weblink.getId()+" - "+weblink.getHtmlPage().substring(0,20));
				System.out.println(weblink.getId()+" - Indexed");
			}  catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) throws InterruptedException, ExecutionException 
	{
		ExecutorsNotifyAllIndexer executorsNotifyAllIndexer = new ExecutorsNotifyAllIndexer();
		executorsNotifyAllIndexer.add(executorsNotifyAllIndexer.createWeblink(2001, "Wikipedia", "https://en.wikipedia.org/wiki/Google", "https://en.wikipedia.org/wiki/Google"));
		executorsNotifyAllIndexer.add(executorsNotifyAllIndexer.createWeblink(2002, "Tomcat", "https://tomcat.apache.org/", "https://tomcat.apache.org/"));
		executorsNotifyAllIndexer.add(executorsNotifyAllIndexer.createWeblink(2003, "StackOverflow", "https://stackoverflow.com/", "https://stackoverflow.com/"));
		executorsNotifyAllIndexer.add(executorsNotifyAllIndexer.createWeblink(2004, "StackOverflow", "https://en.wikipedia.org/wiki/Visual_Studio_Code", "https://en.wikipedia.org/wiki/Visual_Studio_Code"));
		executorsNotifyAllIndexer.go();
	}
	public void go() throws InterruptedException, ExecutionException
	{
		List<DownloaderThread<Weblink>> dl =  new ArrayList<>();
		List<Future<Weblink>> futureList = new ArrayList<>();
		long currentTime = System.nanoTime() + TIME_FRAME;
		while(queue.size()>0) 
		{
			Weblink weblink = queue.remove();
			dl.add(new DownloaderThread<Weblink>(weblink));
		}
		futureList = downloaderService.invokeAll(dl,currentTime-System.nanoTime(),TimeUnit.NANOSECONDS); 
		
		for (Future<Weblink> future : futureList) 
		{
			if(future.isCancelled())
			{
				System.out.println("Task Cancelled");
			}
			else
			{
				indexerService.execute(new IndexerThread(future));
			}
		}
		downloaderService.shutdown();
		indexerService.shutdown();
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
	    
	    private volatile String htmlPage;
	    
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
	}
}
