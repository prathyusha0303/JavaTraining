package com.rgt.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rgt.bookstore.entity.Book;
import com.rgt.bookstore.repository.BookRepository;

@Service
public class BookServiceImpl {

	@Autowired
	BookRepository bookRepository;

	public Book getBookById(Integer bookid) {
		return bookRepository.findById(bookid).get();
	}

	public void savebook(Book book) {
		bookRepository.save(book);
	}

	public void delete(Integer bookid) {
		bookRepository.deleteById(bookid);
	}

	public Book update(Book book, Integer bookid) {
		book.setBookid(bookid);
		return bookRepository.save(book);
	}
	public List<Book> getBooksByBookName(String bookName) {
        return bookRepository.findByBookName(bookName);
    }

    public Page<Book> getBooksByPage(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber++, pageSize);
        return bookRepository.findAll(pageable);
    }

    public List<Book> getActiveBooks() {
        return bookRepository.findByIsActiveTrue();
    }
}
