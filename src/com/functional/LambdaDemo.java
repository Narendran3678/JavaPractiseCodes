package com.functional;

import java.util.*;

interface FunctionalInterface
{
	public void functionalDemo(String str,Integer in);
	public default void defaultMethod() {
		System.out.println("Default Method");
	}
	String toString();
}
public class LambdaDemo 
{
	public static void main(String args[])
	{
		FunctionalInterface fu = (s1,i1)->{
			System.out.println(s1+"-"+i1);
		};
		fu.defaultMethod();
		fu.functionalDemo("1", 1);
		
		List<String> list = new ArrayList<String>();
		list.add("Naren");
		list.add("Arisu");
		list.add("Deejay");
		
		list.sort((s1,s2) ->{
			return s1.compareTo(s2);
		});
		list.forEach(System.out::println);
	}
}
