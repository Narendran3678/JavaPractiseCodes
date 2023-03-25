package com.functional;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import org.htmlcleaner.HtmlCleaner;

class Indexer {
	private static List<String> stopWords = Arrays.asList("of", "the", "a", "is", "to", "in", "and");
	
	public static boolean conditionCheck(String docs) {
		return docs.contains("stream");
	}

	public static String htmlTagStrip(String docs) {
		return new HtmlCleaner().clean(docs).getText().toString();
	}

	public static String removeStopWords(String docs) {
	
		StringBuilder sb = new StringBuilder();
		for (String word : docs.split(" ")) {
			if (!stopWords.contains(word))
				sb.append(word).append(" ");
		}
		
		return sb.toString();
	}
}

public class FunctionPredicateDemo {
	
	static List<String> finalDocs = new ArrayList<String>();
	static String doc1 = "<html><body>One of the most common uses of <i>streams</i> is to represent queries over data in collections</body></html>";
	static String doc2 = "<html><body>Information integration systems provide valuable services to users by integrating information from a number of autonomous, heterogeneous and distributed Web sources</body></html>";
	static String doc3 = "<html><body>Solr is the popular, blazing fast open source enterprise search platform from the Apache Lucene</body></html>";
	static String doc4 = "<html><body>Java 8 goes one more step ahead and has developed a streams API which lets us think about parallelism</body></html>";
	
													//InputType
	public static boolean filter(String docs, Predicate<String> filter) {
		return filter.test(docs);
	}
													//InputType  ReturnType
	public static String transform(String docs, Function<String, String> filter) {
		return filter.apply(docs);
	}
														     //Input & Return Type
	public static String transform(String docs, UnaryOperator<String> filter) {
		return filter.apply(docs);
	}
	static void imperativeMethod()
	{
		List<String> documents = Arrays.asList(doc1, doc2, doc3, doc4);
		for (String docs : documents) {
			boolean flag=false;
			
			/*flag = filter(docs, (d) -> {
				return Indexer.conditionCheck(d);
			});*/
			BiFunction<String,String,Boolean> condition= (d,s) -> d.contains(s) ;
			flag = condition.apply(docs, "stream");
			
			if(flag)
			{
				/*
				docs = transform(docs,(d)->{
					return Indexer.htmlTagStrip(d);
				});
				
				docs = transform(docs,(d)->{
					return Indexer.removeStopWords(d);
				});
				*/
				Function<String,String> tagStrip= (d)->{
					return Indexer.htmlTagStrip(d);
				};
				Function<String,String> stopWordStrip= (d)->{
					return Indexer.removeStopWords(d);
				};
				Function<String,String> endResult = tagStrip.andThen(stopWordStrip);
				docs = endResult.apply(docs);
				
				finalDocs.add(docs);
			}
			else
			{
				System.out.println("$$$$$$$$$$ Condition Check Failed $$$$$$$$$$");
			}
		}
		finalDocs.forEach(System.out::println);
	}
	static void declarativeMethod()
	{
		Arrays.stream(new String[] { doc1, doc2, doc3, doc4 })
		.filter((d) -> d.contains("stream"))
		.map(Indexer::htmlTagStrip)
		.map(Indexer::removeStopWords)
		.forEach(System.out::println);
	}
	public static void main(String[] args) {
		imperativeMethod();
		declarativeMethod();
		
	}
}
