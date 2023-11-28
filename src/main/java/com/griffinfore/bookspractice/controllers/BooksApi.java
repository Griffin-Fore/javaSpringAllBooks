package com.griffinfore.bookspractice.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.griffinfore.bookspractice.Services.BookService;
import com.griffinfore.bookspractice.models.Book;
@RestController
public class BooksApi {
 private final BookService bookService;
 public BooksApi(BookService bookService){
     this.bookService = bookService;
 }
 @RequestMapping("/api/books")
 public List<Book> index() {
     return bookService.allBooks();
 }
 
 @RequestMapping(value="/api/books", method=RequestMethod.POST)
 public Book create(@RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
     Book book = new Book(title, desc, lang, numOfPages);
     return bookService.createBook(book);
 }
 
 @RequestMapping("/api/books/{id}")
 public Book show(@PathVariable("id") Long id) {
     Book book = bookService.findBook(id);
     return book;
 }
  @RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
  public Book update(
  		@PathVariable("id") Long id, 
  		@RequestParam(value="title") String title, 
  		@RequestParam(value="description") String desc, 
  		@RequestParam(value="language") String lang,
  		@RequestParam(value="pages") Integer numOfPages) {
	  // creating a book with these attributes
      Book book = new Book(title, desc, lang, numOfPages);
      // set the id  of the book
      book.setId(id);
      // update the book at the id
      bookService.updateBook(book);
      return book;
  }
  
  @RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
  // get passed an id
  public void destroy(@PathVariable("id") Long id) {
	  // pass the id to bookService, to the delete method
      bookService.deleteBook(id);
  }
}