package com.io.serialization;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializaitonDemo {
	public static void serializationMethod(Object object) 
	{
		try ( ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(new File("serial.ser")))); )
		{
			oos.writeObject(object);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static Object deSerializationMethod(String serializedFile)
	{
		Object object=null;
		try( ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(new File(serializedFile)))); )
		{
				object = ois.readObject();	
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return object;
	}
	public static void main(String arg[])
	{
		Editor editor = new Editor(1,"OvalOffice");
		Person person = new Person(1,"Naren","naren@gmail.com");
		Person.setPhoneNumber(7092802533l);
		person.setEditor(editor);
		//SerializaitonDemo.serializationMethod(person);
		
		Person serPerson= (Person) SerializaitonDemo.deSerializationMethod("serial.ser");
		System.out.println(serPerson.toString());
		System.out.println(serPerson.getEditor().toString());		
		
	}
}
