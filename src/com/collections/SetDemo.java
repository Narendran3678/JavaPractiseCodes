package com.collections;

import java.util.*;


public class SetDemo 
{
	public static void NavigableSetDemo()
	{

		NavigableSet<String> setStr= new TreeSet<String>();
		setStr.add("1");
		setStr.add("23");
		setStr.add("67");
		setStr.add("50");
		setStr.add("79");
		setStr.add("88");
		
	//	setStr.forEach(System.out::println);
		
		System.out.println("Floor..."+setStr.floor("63")); //>=
		System.out.println("Ceiling..."+setStr.ceiling("63")); //<=
		System.out.println("Lower..."+setStr.lower("67"));//<
		System.out.println("Higher..."+setStr.higher("67")); //>
		
		
	}
	public static void TreeSetDemo()
	{
		Set<String> setStr= new TreeSet<String>();
		setStr.add("1");
		setStr.add("2");
		setStr.add("1");
		setStr.add("4");
		
		System.out.println(setStr);
		
		Set<Passenger> passSet= new TreeSet<Passenger>(new PassengerNameSorter());
		passSet.add(new Passenger("Naren","Naren",27,"Male","A","21340"));
		passSet.add(new Passenger("Naren","Naren",27,"Male","A","21340"));

		passSet.add(new Passenger("John","Sam",23,"Male","B","15740"));
		passSet.add(new Passenger("Bala","Jay",25,"Male","B","35240"));
		passSet.forEach(System.out::println);
	}
	public static void HashSetDemo()
	{
		Set<String> setStr= new HashSet<String>();
		setStr.add("a");
		setStr.add("b");
		setStr.add("a");
		setStr.forEach(System.out::println);
		System.out.println();
		
		Set<Passenger> passSet= new HashSet<Passenger>();
		passSet.add(new Passenger("Naren","Naren",27,"Male","A","21340"));
		passSet.add(new Passenger("John","Sam",23,"Male","B","15740"));
		passSet.add(new Passenger("Naren","Naren",27,"Male","A","21340"));
		passSet.forEach(System.out::println);
		
	}
	public static void main(String args[])
	{
		//SetDemo.HashSetDemo();
		//SetDemo.TreeSetDemo();
		SetDemo.NavigableSetDemo();
	}
}
