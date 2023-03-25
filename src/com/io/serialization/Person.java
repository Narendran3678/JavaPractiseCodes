package com.io.serialization;
import java.io.Serializable;
public class Person implements Serializable {
	/**
	 * serialver com.io.serialization.Person
	 */
	static final long serialVersionUID = -9084519996454638748L;
	private transient int id=0;
	private String name="";
	private String mail="";
	public static long phoneNumber=0l;
	private Editor editor=null;
	public Person() {}
	public Person(int id, String name, String mail) {
		super();
		this.id = id;
		this.name = name;
		this.mail = mail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public static long getPhoneNumber() {
		return phoneNumber;
	}

	public static void setPhoneNumber(long phoneNumber) {
		Person.phoneNumber = phoneNumber;
	}

	public Editor getEditor() {
		return editor;
	}

	public void setEditor(Editor editor) {
		this.editor = editor;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", mail=" + mail + ", phoneNumber="+phoneNumber+"]";
	}
	
}
