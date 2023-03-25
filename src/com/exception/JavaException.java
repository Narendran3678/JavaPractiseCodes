package com.exception;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
class TestClass implements AutoCloseable
{
	public void method() throws IOException
	{
		System.out.println("TestClass Method");
	}
	@Override
	public void close() throws Exception {
		throw new IOException("Test Class Exception");
	}
}
class TestClassA implements AutoCloseable
{
	@Override
	public void close() throws Exception {
		throw new IOException("Test Class A Exception");
	}
}
class TestClass1 extends TestClass
{
	@Override
	public void method() throws FileNotFoundException // While Overriding throws Exception must be subclass of parent method exception
	{
		System.out.println("TestClass1 Method");
	}
}
public class JavaException {
	public static void ExceptionalHandlingWithArm()
	{
		try(TestClass ts=new TestClass(); 
				TestClassA ta=new TestClassA();
				Scanner sc= new Scanner(System.in);
				TestClass1 tc = new TestClass1())
		{
			tc.method();
			throw new FileNotFoundException("File not Found");
		}
		catch(Exception e)
		{
			System.out.println("JavaException");
			e.printStackTrace();
		}
		finally
		{
			
		}
		System.out.println("Hello");
	}
	public static void ExceptionalHandlingWithoutArm() throws Exception 
	{
		TestClass t = null;
		TestClassA t2 = null;
		try
		{
			t=new TestClass(); 
			t2=new TestClassA();
			throw new FileNotFoundException("File not Found");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			if (t2 != null) 
				t2.close();
			
			if (t != null) 
				t.close();			
		}	
	}
	public static void ExceptionalHandlingWithoutArm1() throws Exception 
	{
		Exception exception = new Exception();
		TestClass t = null;
		TestClassA t2 = null;
		try
		{
			t=new TestClass(); 
			t2=new TestClassA();
			throw new FileNotFoundException("File not Found");
		}
		catch(Exception e)
		{
			exception = e;
		}
		finally
		{
			if(exception!=null)
			{
				try
				{
					if(t!=null)
					{
						t.close();
					}
				}
				catch(Exception e)
				{
					exception.addSuppressed(e);
				}
			}	
			else
			{
				try
				{
					if(t!=null)
					{
						t.close();
					}
				}
				catch(Exception e)
				{
					exception = e;
				}
			}
			if(exception!=null)
			{
				try
				{
					if(t2!=null)
					{
						t2.close();
					}
				}
				catch(Exception e)
				{
					exception.addSuppressed(e);
				}
				throw exception;
			}	
			else
			{
				try
				{
					if(t2!=null)
					{
						t2.close();
					}
				}
				catch(Exception e)
				{
					exception = e;
				}
			}
			
		}	
	}
	public static void main(String args[]) 
	{
		try {
			JavaException.ExceptionalHandlingWithoutArm1();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
}


