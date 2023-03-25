package com.concurrency.Executors;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.java.utils.IOUtils;

/**
 * Uses only 4 threads. Improvises FutureIndexer
 *
 * Note: htmlPage is NOT declared volatile in Weblink due to the below 
 *         memory visibility guarantee
 * 
 * Memory Visibility: Actions in a thread prior to the submission of a 
 *         Runnable or Callable task to an ExecutorService happen-before any 
 *         actions taken by that task, which in turn happen-before the result 
 *         is retrieved via Future.get().
 *
 * Benefit: 
 *    Executor framework is used. So, we get thread management benefit!
 *    Better coordination of control flow between threads. Cleaner than wait-notify
 *    Thread blocking is no longer an issue
 *    
 * Difference from FutureTimedGetIndexer:
 *    Uses ExecutorService's timed invokeAll method to submit all tasks. 
 *    Uses each Future's isCancelled & get method for indexing
 *  
 * @author Dheeru Mundluru
 *
 */

public class TimedInvokeAllIndexer {
	private Deque<Weblink> queue = new ArrayDeque<>();
	
	// Executors
	ExecutorService downloadExecutor = Executors.newFixedThreadPool(2);
	ExecutorService indexerExecutor = Executors.newFixedThreadPool(2);
	
	private static final long TIME_FRAME = 2000000000L; // 2 seconds
	
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
	
	private static class Downloader<T extends Weblink> implements Callable<T> {
		private T weblink;
		public Downloader(T weblink) {
			this.weblink = weblink;
		}
		public T call() {
			try {
				String htmlPage = IOUtils.read(IOUtils.httpConnect(weblink.getUrl()));
				weblink.setHtmlPage(htmlPage);				
			}  catch (Exception e) {
				e.printStackTrace();
			}
			return weblink;
		}
	}
	
	private static class Indexer implements Runnable {
		private Weblink weblink;
		private Indexer(Weblink weblink) {
			this.weblink = weblink;
		}
		public void run() {			
			String htmlPage = weblink.getHtmlPage();
			index(htmlPage);
		}
		private void index(String text) {
			if (text != null) {
				System.out.println("\nIndexed: " + weblink.getId() + "\n");
			}
		}
	}
	
	public void go() {
		long endTime = System.nanoTime() + TIME_FRAME;
		List<Downloader<Weblink>> tasks = new ArrayList<>();
		List<Future<Weblink>> futures = new ArrayList<>();
				
		while (queue.size() > 0) {
			Weblink weblink = queue.remove();
			tasks.add(new Downloader<Weblink>(weblink));			
		}
		
		long timeLeft = endTime - System.nanoTime();
		try {
			futures = downloadExecutor.invokeAll(tasks, timeLeft, TimeUnit.NANOSECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (Future<Weblink> future : futures) {
			try {
				if (!future.isCancelled()) {
					indexerExecutor.execute(new Indexer(future.get()));
				} else {
					System.out.println("\n\nTask is cancelled --> " + Thread.currentThread());
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
			
		downloadExecutor.shutdown();
		indexerExecutor.shutdown();
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
	
	public static void main(String[] args) {
		
		TimedInvokeAllIndexer timedInvokeAllIndexer = new TimedInvokeAllIndexer();
		timedInvokeAllIndexer.add(timedInvokeAllIndexer.createWeblink(2001, "Wikipedia", "https://en.wikipedia.org/wiki/Google", "https://en.wikipedia.org/wiki/Google"));
		timedInvokeAllIndexer.add(timedInvokeAllIndexer.createWeblink(2002, "Tomcat", "https://tomcat.apache.org/", "https://tomcat.apache.org/"));
		timedInvokeAllIndexer.add(timedInvokeAllIndexer.createWeblink(2003, "StackOverflow", "https://stackoverflow.com/", "https://stackoverflow.com/"));
		timedInvokeAllIndexer.add(timedInvokeAllIndexer.createWeblink(2004, "StackOverflow", "https://en.wikipedia.org/wiki/Visual_Studio_Code", "https://en.wikipedia.org/wiki/Visual_Studio_Code"));
		timedInvokeAllIndexer.go();
	}
}
