package com.oop;
class Demo 
{
	
}
public class TestClient implements Cloneable{
	int tc=1;
   public static int getVal() {
       return 42;
   }
   
	@Override
	protected TestClient clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (TestClient)super.clone();
	}

public static void main(String[] args) throws CloneNotSupportedException {
       C c = new X();
       c.go();
	   c.foo();
	   c.bar();
	   c.foobar();
	 /*TestClient t=new TestClient();
	 t.tc=10;
     Object o=t.clone();
     
     if(o instanceof TestClient)
     {
    	 TestClient t1=(TestClient)o;
  	  	System.out.println(o.getClass().getName());
  	   	System.out.println(t1.tc);
  	   	t1.tc=100;
  		System.out.println(t.tc);
  	 	System.out.println(t1.tc);
     }*/
   }
}