package com.functional;

import java.util.Objects;

public class Book {		
	private long isbn;
	private String title;	
	private double rating;
	private double price;
	private String source;
	
	public Book(long isbn, String title, double rating, double price, String source) {
		this.isbn = isbn;
		this.title = title;
		this.rating = rating;
		this.price = price;
		this.source = source;
	}
	
	public long getIsbn() {
		return isbn;
	}
	
	public String getTitle() {
		return title;
	}
	
	public double getRating() {
		return rating;
	}
	
	public double getPrice() {
		return price;
	}
	
	public String getSource() {
		return source;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(isbn);
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
		return isbn == other.isbn;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", rating=" + rating + ", price=" + price + ", source="
				+ source + "]";
	}
	
}	
