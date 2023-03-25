package com.exception;

class DemoClass
{
	public static void raiseExepction() throws TypeConversionException
	{
		String str="Naren";
		try
		{
			int value= Integer.valueOf(str);
		}
		catch(NumberFormatException e)
		{
			throw new TypeConversionException("Number format Exception["+str+"]",e);
		}
	}
}
public class CustomException 
{
	public static void main(String args[]) 
	{
		try {
			DemoClass.raiseExepction();
		} catch (TypeConversionException e) {
			e.printStackTrace();
			//e.getCause().printStackTrace();
		}
		
	}
}
