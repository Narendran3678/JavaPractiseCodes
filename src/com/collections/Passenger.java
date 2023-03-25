package com.collections;

import java.util.Comparator;

public class Passenger implements Comparable<Passenger>{
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	private String travelClass;
	private String confirmationNumber;
	
	public Passenger(String firstName, String lastName, int age, String gender, String travelClass,
			String confirmationNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.travelClass = travelClass;
		this.confirmationNumber = confirmationNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public int getAge() {
		return age;
	}
	public String getGender() {
		return gender;
	}	
	public String getTravelClass() {
		return travelClass;
	}
	public String getConfirmationNumber() {
		return confirmationNumber;
	}
	@Override
	public int hashCode()
	{
		int result = 1;
		int randVal= 31;
		result = randVal + result + firstName.hashCode() ;
		result = randVal + result + confirmationNumber.hashCode() ;
		return result;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		Passenger passObj = (Passenger)obj;
		if(getFirstName().equals(passObj.getFirstName()) &&
				getConfirmationNumber().equals(passObj.getConfirmationNumber()))
			return true;
		else
			return false;
	}	
	
	@Override
	public String toString() {
		return "Passenger [firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + ", gender=" + gender
				+ ", travelClass=" + travelClass + ", confirmationNumber=" + confirmationNumber + "]";
	}


	@Override
	public int compareTo(Passenger o) {
		// TODO Auto-generated method stub
		return firstName.compareTo(o.getFirstName());
	}	
	
}
class PassengerAgeSorter implements Comparator<Passenger>
{
	@Override
	public int compare(Passenger o1, Passenger o2) {
		if(o1.getAge()>o2.getAge())
			return 1;
		else if(o1.getAge()<o2.getAge())
			return -1;
		else
			return 0;
	}
}
class PassengerNameSorter implements Comparator<Passenger>
{
	@Override
	public int compare(Passenger o1, Passenger o2) {
		return o1.getFirstName().compareTo(o2.getFirstName());
	}
}