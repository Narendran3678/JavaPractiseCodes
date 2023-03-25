package com.concurrency.threadCopMgmt;
import java.io.InputStream;
import java.util.*;

import com.java.utils.IOUtils;
public class WaitNotifyIndexer 
{
	Deque<Weblink> queue = new ArrayDeque<Weblink>();
	private class Downloader implements Runnable
	{
		private Weblink weblink;
		private Downloader(Weblink weblink)
		{
			this.weblink=weblink;
		}
		@Override
		public void run() {
			synchronized (weblink) {
				InputStream is = IOUtils.httpConnect(weblink.getUrl());
				weblink.setHtmlPage(IOUtils.read(is));
				weblink.notifyAll();
			}
		}
	}
	private class Indexer implements Runnable
	{
		private Weblink weblink;
		private Indexer(Weblink weblink)
		{
			this.weblink=weblink;
		}
		@Override
		public void run() 
		{
			int i=0;
			synchronized (weblink) {
				String webPage=weblink.getHtmlPage();
				while(webPage==null)
				{
					try 
					{
						weblink.wait();
						webPage=weblink.getHtmlPage();
					} 
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				indexPage(weblink);
			}
		}	
	}
	private void indexPage(Weblink weblink)
	{
		System.out.println("WebPage "+weblink.getId()+" Indexed");	
	}
	public static void main(String args[])
	{
		NaiveIndexer naiveIndexer = new NaiveIndexer();
		naiveIndexer.add(naiveIndexer.createWeblink(2000, "Wikipedia", "https://en.wikipedia.org/wiki/Google", "https://en.wikipedia.org/wiki/Google"));
		naiveIndexer.add(naiveIndexer.createWeblink(2002, "Tomcat", "https://tomcat.apache.org/", "https://tomcat.apache.org/"));
		naiveIndexer.add(naiveIndexer.createWeblink(2004, "StackOverflow", "https://stackoverflow.com/", "https://stackoverflow.com/"));
		naiveIndexer.go();
	}
	public void go()
	{
		while(queue.size()>0)
		{
			Weblink wl = queue.remove();
			
			Thread downloaderThread = new Thread(new Downloader(wl),"Downloader");
			Thread inxderThread = new Thread(new Indexer(wl),"Indexer");
			
			downloaderThread.start();
			inxderThread.start();
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
	}
}
