package com.aurionpro.collectionTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import com.aurionpro.collection.Book;
import com.aurionpro.collection.AuthorComparator;
import com.aurionpro.collection.TitleComparator;

public class BookTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scanner=new Scanner(System.in);

		
		
		List<Book> booksMain=new ArrayList<Book>();
		List<Book> issuedBooks=new ArrayList<Book>();
		List<Book> availableBooks=new ArrayList<Book>();
		
		Set<Integer> issuedBooksId=new HashSet<Integer>();
		Set<Integer> availableBooksId=new HashSet<Integer>();
		
		
		booksMain.add(new Book(1, "The Alchemist", "Paulo Coelho", "HarperCollins"));
		booksMain.add(new Book(2, "1984", "George Orwell", "Penguin"));
		booksMain.add(new Book(3, "To Kill a Mockingbird", "Harper Lee", "J.B. Lippincott & Co."));
		booksMain.add(new Book(4, "The Great Gatsby", "F. Scott Fitzgerald", "Charles Scribner's Sons"));
		booksMain.add(new Book(5, "Moby Dick", "Herman Melville", "Harper & Brothers"));
		
		
		availableBooks.addAll(booksMain);
		for (Book book : booksMain) {
		    availableBooksId.add(book.getId());
		}
		
		
			int userInput;
		
		do {
	         System.out.println("\n====== Book Library Menu ======");
	            System.out.println("1. Add Book");
	            System.out.println("2. Issue Book");
	            System.out.println("3. View Available Books");
	            System.out.println("4. View Issued Books");
	            System.out.println("5. Return Book");
	            System.out.println("6. Sort Books");
	            System.out.println("7. Exit");
	            System.out.print("Enter your choice: ");
	            
	            userInput=scanner.nextInt();
	            scanner.nextLine(); 
	            
			
			
			switch (userInput) {
				case 1:
					//+1 to get the id of book to add
					int newBookId=(booksMain.get(booksMain.size()-1).getId())+1;
					  System.out.print("Enter Book Title: ");
					String title=scanner.nextLine();
					System.out.print("Enter Book Author: ");
					String author=scanner.nextLine();
					  System.out.print("Enter Book Publisher: ");
				String publisher=scanner.nextLine();
				
				availableBooksId.add(newBookId);
									Book bookToAdd=new Book(newBookId,title,author,publisher);
					booksMain.add(bookToAdd);
					availableBooks.add(bookToAdd);
					 System.out.println("Book added with ID: " + newBookId);
					 break;
					
				case 2:
					 System.out.print("Enter Book ID to issue: ");
					int bookIdToIssue=scanner.nextInt();
					
					Book bookToIssue=findBookById(booksMain,bookIdToIssue);
					  if (bookToIssue != null && availableBooksId.contains(bookIdToIssue)) {
	                        issuedBooks.add(bookToIssue);
	                        issuedBooksId.add(bookIdToIssue);
	                        availableBooks.remove(bookToIssue);
	                        availableBooksId.remove(bookIdToIssue);
	                        System.out.println("Book issued successfully.");
	                    } else {
	                        System.out.println("Book not available or does not exist.");
	                    }
	                    break;
					
				case 3:
					System.out.println("Available Books:");
					for(Book book:availableBooks) {
						System.out.println(book);
					}
					//System.out.println(availableBooks);
					
					break;
				case 4:
					System.out.println("Issued Books:");
				//	System.out.println(issuedBooks);
					for(Book book:issuedBooks) {
						System.out.println(book);
					}
					break;
				case 5:
					System.out.println("Enter book id to return");
					 int bookIdToReturn = scanner.nextInt();

	                    Book bookToReturn = findBookById(booksMain, bookIdToReturn);
	                    if (bookToReturn != null && issuedBooksId.contains(bookIdToReturn)) {
	                        availableBooks.add(bookToReturn);
	                        availableBooksId.add(bookIdToReturn);
	                        issuedBooks.remove(bookToReturn);
	                        issuedBooksId.remove(bookIdToReturn);
	                        System.out.println("Book returned successfully.");
	                    } else {
	                        System.out.println("Invalid book ID or book not issued.");
	                    }
	                    break;
					
				case 6:
					 System.out.println("Sort by:\n1. Author\n2. Title");
					int sortBy=scanner.nextInt();
					if(sortBy==1) {
						Collections.sort(booksMain,new AuthorComparator());
					}
					else {
						Collections.sort(booksMain,new TitleComparator());
					}
					for(Book book:booksMain) {
						System.out.println(book);
					}
					//System.out.println(booksMain);
					break;
					
				case 7:
					
					System.out.println("Exiting..");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					return;
					
					
			}
		}while(userInput!=7);
		

	
		
	}
	
    private  static Book findBookById(List<Book> books, int id) {
        for (Book book : books) {
            if (book.getId()==id) {
                return book; // Found the book
            }
        }
        return null; // Book not found
    }

}
