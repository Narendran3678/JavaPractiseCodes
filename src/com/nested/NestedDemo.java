package com.nested;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class NestedDemo {
	
	public static Set<Book> treeSetDemo(Comparator<Book> comparator) {
		Book book1 = new Book("Harry Potter", "J.K.Rowling", 1997);
		Book book2 = new Book("Harry Potter", "J.K.Rowling", 1997);
		Book book3 = new Book("Walden", "Henry David Thoreau", 1854);
		Book book4 = new Book("Effective Java", "Joshua Bloch", 2008);
		Book book5 = new Book("The Last Lecture", "Randy Pausch", 2008);
			  
		Set<Book> books = null;
		if (comparator == null) {
			books = new TreeSet<>();
		} else {
			books = new TreeSet<>(comparator);
		}
		
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		books.add(book5);
	      
	    for (Book book : books) {
	    	System.out.println(book);
	    }
	    
	    return books;
	}	
	public static void main(String args[])
	{
		NestedDemo.treeSetDemo(Book.TITLE_COMPARATOR);
		System.out.println();
		NestedDemo.treeSetDemo(new Book.PubDateComparators.PubDateAscComparator());
		System.out.println();
		NestedDemo.treeSetDemo(new Book.PubDateComparators.PubDateDescComparator());
		System.out.println();
	}
}