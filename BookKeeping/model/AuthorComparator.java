package com.aurionpro.collection;

import java.util.Comparator;

public class AuthorComparator implements Comparator<Book>{

	@Override
	public int compare(Book book1, Book book2) {
		// TODO Auto-generated method stub
		
		return book1.getAuthor().compareTo(book2.getAuthor());
		
			
		
		
	}

		
	
	
}
