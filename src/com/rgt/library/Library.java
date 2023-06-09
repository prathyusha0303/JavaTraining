package com.rgt.library;

public class Library {

	private static final int MAX_BOOKS = 2;
	private static final int MAX_PATRONS = 2;

	private Book[] bookTitles = new Book[MAX_BOOKS];
	private Patron[] patronIn = new Patron[MAX_PATRONS];
	
	public int bookcount;
	public int patroncount;

	public Library() {
		bookcount = 0;
		patroncount = 0;
	}

	public void addbooks(Book book) {
		if (bookcount < MAX_BOOKS) {
			bookTitles[bookcount++]=book;
			System.out.println("Book is added successfully");
		}else {
			System.out.println("Books limit is over.");
		}
	}

	public void addpatrons(Patron patron) {
		if(patroncount<MAX_PATRONS) {
			patronIn[patroncount++]=patron;
			System.out.println("Patron is added sucessfully");
		}else {
			System.out.println("Patron limit is over");
		}
	}
	
	public void borrowbooks(String patrons,String bookname) {
		Book book=getbook(bookname);
		Patron patron=getpatron(patrons);
		if(book !=null && patron != null) {
			if(book.isAvailable()) {
				System.out.println("book is not available");
			}
			else {
				book.setAvailable(false);
				book.setBorrow(patron);
				System.out.println("Book is borrowed successfully.");
			}
		}else {
				System.out.println("Book is already borrowed by other patron");
			}
		}
	
	private Patron getpatron(String patrons) {
		for(int i=0;i<patroncount;i++) {
			if(patronIn[i].getPatronName().equalsIgnoreCase(patrons)) {
				return patronIn[i];
			}
		}
		return null;
	}

	private Book getbook(String bookname) {
		for(int i=0; i<bookcount; i++) {
			if(bookTitles[i].getTitle().equalsIgnoreCase(bookname)) {
				return bookTitles[i];
			}
		}
		return null;
	}

	public void returnbooks(String namepatron, String namebook) {
		Book book=getbook(namebook);
		if(book !=null && !book.isAvailable()) {
			book.setAvailable(true);
			book.setBorrow(null);
			System.out.println("book return successfully");
		}else {
			System.out.println("book is already returned");
		}
		
	}
}

