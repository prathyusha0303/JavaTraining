package com.rgt.library;

public class Book {

	private String title;
	private String author;
	private boolean available;
	private Patron borrow;
	
	
	public Book(String title, String author) {
		super();
		this.title = title;
		this.author = author;
		this.available=true;
		this.borrow=null;
	}
	public Book() {
		
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
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public Patron getBorrow() {
		return borrow;
	}
	public void setBorrow(Patron borrow) {
		this.borrow = borrow;
	}
	
}
