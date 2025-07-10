package com.aurionpro.collection;

public class Book {

	@Override
	public String toString() {
	    return "Book ID: " + id +
	           "\nTitle   : " + title +
	           "\nAuthor  : " + author +
	           "\nPublisher: " + publisher + "\n\n";
	}


	String title,publisher,author;
	int id;
	
	public String getTitle() {
		return title;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getAuthor() {
		return author;
	}

	public int getId() {
		return id;
	}

	public Book(int id,String title, String author,String publisher) {
		super();
		this.title = title;
		this.publisher = publisher;
		this.author = author;
		this.id = id;
	}

	
	
	
}
