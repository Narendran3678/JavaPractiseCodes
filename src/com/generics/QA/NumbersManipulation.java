package com.generics.QA;

import java.util.*;

interface OperationInterface<T>
{
	 public boolean test(T t);
}
class OddOperation<T> implements OperationInterface<Integer>
{
	@Override
	public boolean test(Integer t) {
		if(t % 2 == 1)
			return true;
		else
			return false;
	}
}
class PrimeOperation<T> implements OperationInterface<Integer>
{
	@Override
	public boolean test(Integer t) 
	{
		if(t==0 && t==1)
			return true;
		for(int i=2;i<=t/2;i++)
		{
			if(t%i==0)
			{
				return true;
			}
		}
		return false;
	}
}
class NumberOperation<T> 
{
	public NumberOperation(){}
	public <T> List<T> manipulate(List<T> t,OperationInterface<T> operation)
	{
		List<T> list= new ArrayList<>();
		for(T a:t)
		{
			if(!operation.test(a))
			{
				list.add(a);
			}	
		}
		return list;
	}
}
public class NumbersManipulation {
	public static void main(String args[])
	{
		NumberOperation<Integer> no= new NumberOperation<>();
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);		
		list = no.manipulate(list, new OddOperation<Integer>());
		System.out.println("Odd Operation..."+list);
		
		list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);		
		list = no.manipulate(list, new PrimeOperation<Integer>());
		System.out.println("Prime Operation..."+list);
		
	}
}	
