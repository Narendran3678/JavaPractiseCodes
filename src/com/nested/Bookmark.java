package com.nested;

public class Bookmark
{
	private long id;
	private String title;
			
	public Bookmark(long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Bookmark [id=" + id + ", title=" + title + "]";
	}
	
}