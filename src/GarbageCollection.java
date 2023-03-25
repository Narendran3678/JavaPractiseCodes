@SuppressWarnings("unused")
public class GarbageCollection {
	
	static int [] arr=new int[12*234*1024];
	public static void main(String args[])
	{
		
		GarbageCollection gc = new GarbageCollection();
		/*-Xms13m -Xmx13m -XX:+PrintGCDetails -XX:+UseSerialGC -XX:MaxNewSize=1m
		 * -Xms13m - Minimum Memory Allocated 
		 * -Xmx13m - Maximum Memory Allocated
		 * -XX:+UseSerialGC - GC Types
		 * -XX:MaxNewSize=1m - Extend the memory after out of memory
	 	*/
	}
	
}