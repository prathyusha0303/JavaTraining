package com.rgt.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rgt.bookstore.entity.Book;
import com.rgt.bookstore.service.BookServiceImpl;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookServiceImpl bookServiceImpl;
	
	@PostMapping
	private Book saveBook(@RequestBody Book book) {
		bookServiceImpl.savebook(book);
		return book;
	}

	@GetMapping
	public ResponseEntity<Page<Book>> getBooksByPage(@RequestParam(defaultValue = "1") Integer pageNumber,
			@RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Book> booksPage = bookServiceImpl.getBooksByPage(pageNumber, pageSize);
		return ResponseEntity.ok(booksPage);
	}
	
	@GetMapping("/{bookid}")
	private Book getBooks(@PathVariable("bookid") Integer bookid) {
		return bookServiceImpl.getBookById(bookid);
	}
	@GetMapping("/search")
	public ResponseEntity<List<Book>> getBooksByBookName(@RequestParam String bookName) {
		List<Book> books = bookServiceImpl.getBooksByBookName(bookName);
		return ResponseEntity.ok(books);
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<Book>> getActiveBooks() {
		List<Book> activeBooks = bookServiceImpl.getActiveBooks();
		return ResponseEntity.ok(activeBooks);
	}
	@PutMapping("/{bookid}")
	private Book update(@RequestBody Book book, @PathVariable("bookid") Integer bookid) {
		bookServiceImpl.update(book, bookid);
		return book;
	}
	
	@DeleteMapping("/{bookid}")
	private void deleteBook(@PathVariable("bookid") Integer bookid) {
		bookServiceImpl.delete(bookid);
	}

}
