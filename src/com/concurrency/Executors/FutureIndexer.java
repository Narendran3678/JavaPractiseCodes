package com.concurrency.Executors;

import java.io.InputStream;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.java.utils.IOUtils;

public class FutureIndexer 
{
	Deque<Weblink> queue = new ArrayDeque<Weblink>();
	ExecutorService downloaderService 	= Executors.newFixedThreadPool(10);
	ExecutorService indexerService 		= Executors.newFixedThreadPool(10);
	private class DownloaderThread<T extends Weblink> implements Callable<T>
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
	private class IndexerThread implements Runnable
	{
		private Weblink weblink;
		private IndexerThread(Weblink weblink)
		{
			this.weblink=weblink;
		}
	
		@Override
		public void run() {
			System.out.println(weblink.getId()+" - "+weblink.getHtmlPage().substring(0,20));
			System.out.println(weblink.getId()+" - Indexed");
		}
	}
	public static void main(String[] args) 
	{
		FutureIndexer futureIndexer = new FutureIndexer();
		futureIndexer.add(futureIndexer.createWeblink(2001, "Wikipedia", "https://en.wikipedia.org/wiki/Google", "https://en.wikipedia.org/wiki/Google"));
		futureIndexer.add(futureIndexer.createWeblink(2002, "Tomcat", "https://tomcat.apache.org/", "https://tomcat.apache.org/"));
		futureIndexer.add(futureIndexer.createWeblink(2003, "StackOverflow", "https://stackoverflow.com/", "https://stackoverflow.com/"));
		try {
			futureIndexer.go();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}
	public void go() throws InterruptedException, ExecutionException
	{
		List<Future<Weblink>> futureList = new ArrayList<>();
		while(queue.size()>0)
		{
			Weblink weblink = queue.remove();
			futureList.add(downloaderService.submit(new DownloaderThread<Weblink>(weblink)));
		}
		
		for (Future<Weblink> future : futureList) 
		{
			indexerService.submit(new IndexerThread(future.get()));
		}
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
