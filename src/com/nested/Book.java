package com.nested;

import java.util.Comparator;

public class Book implements Comparable {
	private String title;
	private String author;
	private int year;
	
	// Your anonymous class should be defined in the following statement
	public static final Comparator<Book> TITLE_COMPARATOR = new Comparator<Book>() {
		@Override
		public int compare(Book o1, Book o2) {
			return o1.getTitle().compareTo(o2.getTitle());
		}
	};
	
	// Your public static member class PubDateComparators can go here!
	public static class PubDateComparators 
	{
		public static class PubDateAscComparator implements Comparator<Book>
		{
			@Override
			public int compare(Book o1, Book o2) {
				if(o1.getYear() > o2.getYear())
					return 1;
				else if(o1.getYear() < o2.getYear())
					return -1;
				else
					return o1.getTitle().compareTo(o2.getTitle());
				
			}	
		}
		
		public static class PubDateDescComparator implements Comparator<Book> 
		{
			@Override
			public int compare(Book o1, Book o2) {
				if(o1.getYear() > o2.getYear())
					return -1;
				else if(o1.getYear() < o2.getYear())
					return 1;
				else
					return o1.getTitle().compareTo(o2.getTitle());
			}	
		}
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public Book(String title, String author, int year) {
		super();
		this.title = title;
		this.author = author;
		this.year = year;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", year=" + year + "]";
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
	/*public int hashCode() {
		return title.hashCode();
	}

	public boolean equals(Object o) {
		return (year == (((Book)o).getYear())) && (author.equals((((Book)o).getAuthor())));
	}*/	
	
	public int compareTo(Object book) {
		 return getTitle().compareTo(((Book)book).getTitle()); // utilizing String’s compareTo
	}	
}
