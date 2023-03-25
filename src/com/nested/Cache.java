package com.nested;

import java.util.*;
import java.util.Map.Entry;

interface CacheIterator<K,V>
{
	public boolean hasNext();
	public CacheEntry<K,V> next();
}
interface CacheEntry<K,V>
{
	public K getKey();
	public V getValue();
}
interface CacheInterface<K,V>
{
	public void add(K key,V value);
	public V getValue(K key);	
	public CacheIterator<K,V> iterator();
}
public class Cache<K,V> implements CacheInterface<K,V>
{
	private List<CacheEntry<K,V>> cacheList = new ArrayList<>();
	private Map<K,V> cacheSet = new HashMap<K,V>();
	@Override
	public void add(K key, V value) 
	{
		cacheSet.put(key,value);
	}

	@Override
	public V getValue(K key) {
		
		return cacheSet.get(key);
	}

	@Override
	public CacheIterator<K, V> iterator() {
		for(Entry<K, V> entry:cacheSet.entrySet() )
		{
			cacheList.add(new CacheEntryImpl(entry.getKey(),entry.getValue()));
		}
		return new MyCacheIterator();
	}
	
	
	private class MyCacheIterator implements CacheIterator<K,V>
	{
		int i=0;
		@Override
		public boolean hasNext() {
			return i < cacheSet.size();
		}
	
		@Override
		public CacheEntry<K, V> next() {
			
			return (CacheEntry<K, V>) cacheList.get(i++);
		}
		
	}
	private class CacheEntryImpl implements CacheEntry<K,V>
	{
		private K key;
		private V value;
		public CacheEntryImpl(K key,V value)
		{
			this.key=key;
			this.value=value;
		}
		@Override
		public K getKey() {
			return key;
		}
		@Override
		public V getValue() {
			return value;
		}
		@Override
		public String toString() {
			return "[key=" + key + ", value=" + value + "]";
		}	
	}
}
