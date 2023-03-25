package com.collections;

import java.util.*;

public class ListDemo 
{
	public ListDemo() {	}
	public static void ArrayDequeDemo()
	{
		Deque<Integer> arrDeq= new ArrayDeque<Integer>();
		arrDeq.add(1);
		arrDeq.add(2);
		arrDeq.add(3);
		arrDeq.add(4);
		arrDeq.add(5);
		arrDeq.add(6);
		arrDeq.add(7);
		arrDeq.add(8);
		
		arrDeq.addFirst(0);
		arrDeq.addLast(9);
		
		if(arrDeq.contains(1))
			System.out.println("Contains 1");

		Collection<Integer> col= new ArrayList<Integer>();
		col.add(10);
		
		arrDeq.addAll(col);
		
		System.out.println(arrDeq.removeFirst());
		System.out.println();
		System.out.println(arrDeq.pop());
		System.out.println();
		for(Integer arr:arrDeq)
			System.out.println(arr);
		
	}
	public static void ArrayListDemo()
	
	{
		List<String> list= new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		list.add("6");
		list.add("7");
		list.add("8");
		/*
		List<String> list1 = list.subList(2, 4);
	//	list.remove(3);
		//list1.set(0, "1.5");
		
		Iterator<String> iterator = list.iterator();
		String str="";
		while(iterator.hasNext())
		{
			str=iterator.next();
			System.out.print(str+" ");
			//if(str=="3")
				//iterator.remove();
		}
		System.out.println();
		for(String s:list)
		{
			if(s=="4")
			//	iterator.remove();
			System.out.print(s+" ");
		}
		System.out.println();
		list1.forEach(System.out::print);*/
		for(ListIterator<String> listItr=list.listIterator();listItr.hasNext();)
		{
			if(listItr.nextIndex()==1)
			{
				listItr.add("1.5");
				System.out.println("Added");
				System.out.println(listItr.nextIndex());
				listItr.previous();
				listItr.set("0.1");
				listItr.set("0.2");
			}
			
			System.out.println(listItr.nextIndex());
			//if(listItr.nextIndex()==3)
				//listItr.remove();
		}
		System.out.println();
		list.forEach(System.out::println);
	}
	
	public static void main(String args[])
	{
		//ListDemo.ArrayListDemo();
		ListDemo.ArrayDequeDemo();
	}
}
