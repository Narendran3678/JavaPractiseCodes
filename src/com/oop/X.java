package com.oop;
public class X extends AbstractA implements A, B, C {
	public static void method1()
	   {
		   System.out.print("A Method1");
	   }
   public void foo() {
	   System.out.println("X: foo");
	   System.out.println("VAL: " + B.VAL);
   }
   public void foobar() {
	   System.out.println("X: foobar");
   }
   public void go()
   {
	  // C.super.go();
	   System.out.println("X Go");
   }
}