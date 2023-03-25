package com.reflection;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ReflectionDemo 
{
	public static Class getClassDetails(String clazz)
	{
		Class userCls =null;
		try 
		{
			int count=0;
			userCls = Class.forName(clazz);
			System.out.println("Class Name..."+userCls.getName());
			System.out.println("Class's Interface Implemented....."+userCls.getInterfaces().length);
			System.out.println("Class's SuperClass....."+userCls.getSuperclass().getName());
			System.out.println();
			Constructor[] classConstructor = userCls.getDeclaredConstructors();
			System.out.println("User Class Constructor......");
			for(Constructor constructor:classConstructor)
			{
				System.out.println("\tConstructor Parameter Count "+ ++count +".....["+constructor.getParameterCount()+"]");
				Parameter[] consParamter = constructor.getParameters();
				for(Parameter paramter : consParamter)
				{
					System.out.println("\t\t"+paramter.getType());
				}
			}
			System.out.println();
			Method[] classMethod = userCls.getDeclaredMethods();
			count=0;
			System.out.println("User Class Method......");
			for(Method method:classMethod)
			{
				System.out.println("\tMethod "+ ++count +"....."+method.getName());
				Parameter[] methParamter = method.getParameters();
				for(Parameter paramter : methParamter)
				{
					System.out.println("\t\t"+paramter.getType());
				}
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return userCls;
	}
	public static void invokeClass(Class clazz)
	{
		try 
		{
			//Constructor Invoking Method 2
			Object object= clazz.newInstance();
			Method m=clazz.getDeclaredMethod("saveWebLink", String.class,String.class);
			m.invoke(object,"http://google.com", "Google");
			
			//Constructor Invoking Method 2
			Constructor cons= clazz.getDeclaredConstructor(int.class, String.class, String.class, String.class, String.class);
			User user =(User) cons.newInstance(101, "john", "john@semanticsquare.com", "male", "user");
			user.getUserDetails();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String args[])
	{
		Class clazz = getClassDetails("com.reflection.User");
		invokeClass(clazz);
	}
}
