package com.oop;
public interface B {
   int VAL = TestClient.getVal();
   void foo();
   public static void method1()
   {
	   System.out.print("B Method1");
   }
   default void go()
   {
	   System.out.println("B Go");
   }
}