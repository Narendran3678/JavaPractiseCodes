package com.oop;
public interface C extends A {
   void foobar();
   default void go()
   {
	   System.out.println("C Go");
   }
}