package com.functional;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

class FunctionalReference
{
	String s;
	static FunctionalReference methodRef= new FunctionalReference();
	public FunctionalReference()
	{	
		s="Default Constructor";
	}
	public FunctionalReference(String s)
	{
		this.s=s;
	}
	public String genMethod(String str) 
	{
		return "This is Method Reference Method - ["+str+"]";
	}
	public static String staticMethod(String str) 
	{
		return "This is Static Method - ["+str+"]";
	}
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	@Override
	public String toString() {
		return "FunctionalReference [s=" + s + "]";
	}
	
}
public class SupplierConsumerDemo {
	

	public static void constructorReferenceDemo()
	{
		Supplier<FunctionalReference> supRef = FunctionalReference::new;
		FunctionalReference fref = supRef.get();
		System.out.println(fref.toString());
		
		Consumer<String> setValue = fref::setS;
		setValue.accept("SetValue for S in Default Constructor");
		System.out.println(fref.toString());
		
		Function<String,FunctionalReference> supParRef = FunctionalReference::new;
		FunctionalReference fpRef=supParRef.apply("Creating Parameterized Constructor");
		System.out.println(fpRef.toString());
		
	}
	
	public static void methodReferenceDemo()
	{
		Function<String,String> staticRef = FunctionalReference::staticMethod;
		System.out.println( staticRef.apply(getSystemInfo()) );

		Function<String,String> methRef = FunctionalReference.methodRef::genMethod;
		System.out.println( methRef.apply(getSystemInfo()) );
	}
	
	public static void main(String[] args) {
		System.out.println("Supplier Demo");
		
		System.out.println(printInfo(() -> {
			return getSystemInfo();
		}));
		constructorReferenceDemo();
		methodReferenceDemo();
	}
	public static String getSystemInfo() {
		return "OS-" + System.getProperty("os.name");
	}

	public static String printInfo(Supplier<Object> supplier) {
		return (String) supplier.get();
	}
}
