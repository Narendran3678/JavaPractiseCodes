package com.generics;

interface Container<T> {
	public T getName();
	public void setName(T t);
}
public class Store<T> implements Container<T>
{
	private T t;
	@Override
	public T getName() {
		return t;
	}

	@Override
	public void setName(T t) {
		this.t=t;
	}
}