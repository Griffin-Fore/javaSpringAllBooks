package com.griffinfore.bookspractice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.griffinfore.bookspractice.Services.BookService;
import com.griffinfore.bookspractice.models.Book;

@Controller
public class BookController {
	    
		private final BookService bookService;
	    
	    public BookController(BookService bookService) {
	        this.bookService = bookService;
	    } 

	    @RequestMapping("/books")
	    public String index(Model model) {
	        List<Book> books = bookService.allBooks();
	        model.addAttribute("books", books);
	        return "index.jsp";
	    }
	    
	@GetMapping("/books/{bookId}")
	public String bookdisplay(Model model, @PathVariable("bookId") Long bookId) {
		System.out.println(bookId);
		
		Book book = bookService.findBook(bookId);
		System.out.println(book);
		model.addAttribute("book", book);

		return "display_book.jsp";
	}
	
}
