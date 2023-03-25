package com.collections;

import java.util.*;
class LRUCache<K, V> extends LinkedHashMap<K, V>
{
	private static final long serialVersionUID = 1L;
	private static final int MAX_ENTRIES = 4;
	public LRUCache(int initialCapacity, float loadFactor, boolean accessOrder) {
		super(initialCapacity, loadFactor, accessOrder);
	}

	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		return size()>MAX_ENTRIES;
	}

}
public class MapDemo 
{
	public static void LRUCacheDemo()
	{
		//Map<String,String> map = new LinkedHashMap<String,String>(16,0.75f,false);   //LRU Method Disable
		//Map<String,String> map = new LinkedHashMap<String,String>(4,0.75f,true); // LRUCache Method Enable
		Map<String,String> map = new LRUCache<String,String>(16,0.75f,true); // LRUCache Remove Least Recently used Element
		
		map.put("a","A");
		map.put("b","B");
		map.put("c","C");
		map.put("d","D");
		System.out.println(map);
		
		map.get("a");
		map.get("a");
		map.get("a");
		System.out.println(map);
		
		map.put("e", "E");
		map.put("f", "F");
		System.out.println(map);
		map.get("e");
		System.out.println(map);
	}
	public static void ImmudatableKeysDemo(){
		List<Integer> list = new ArrayList<Integer>();
		list.add(1);
		
		@SuppressWarnings("rawtypes")
		Map<List,Integer> map = new HashMap<List,Integer>();
		map.put(list, 101);//Map Will store a Hashcode in place of list because array list override the hashcode method

		list.add(2);//Now new hashcode will be generated.
		
		System.out.println(map.get(list)); //SO it will return null;
	}
	public static void HashMapDemo()
	{
		Map<String,Map<String,Object>> person = new HashMap<String,Map<String,Object>>();
		
		Map<String,Object> perDetail= new HashMap<String,Object>();
		perDetail.put("age",27);
		perDetail.put("phone","7092802533");
		perDetail.put("mail","naren@gmail");
		person.put("Narendran",perDetail);
		
		
		perDetail= new HashMap<String,Object>();
		perDetail.put("age",25);
		perDetail.put("phone","6385810492");
		perDetail.put("mail","sam@gmail");
		person.put("Sam",perDetail);
		
		Set<String> setKeys = person.keySet();
		for(String key : setKeys)
		{
			System.out.println("Key.."+key);
			Set<Map.Entry<String, Object>> entrySet =  person.get(key).entrySet();
			for(Map.Entry<String, Object> map:entrySet)
			{
				System.out.println("\tInfoKey..."+map.getKey()+" -> InfoValue..."+map.getValue());
			}
		}
	}
	public static void main(String args[])
	{
		//MapDemo.HashMapDemo();
		//MapDemo.ImmudatableKeysDemo();
		MapDemo.LRUCacheDemo();
	}
}

