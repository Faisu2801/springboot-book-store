package com.faisal.ebook.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.faisal.ebook.store.entity.Book;
import com.faisal.ebook.store.entity.MyBookList;
import com.faisal.ebook.store.service.BookService;
import com.faisal.ebook.store.service.MyBookListService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private MyBookListService myBookService;
	
	
	@GetMapping(value={"/","home",""})
	public String home() {
		return "home";
	}
	
	
	@GetMapping("/book-register")
	public String bookRegister() {
		return "bookRegister";
	}
	
	
	@GetMapping("/available-books")
	public ModelAndView getAllBook() {
		List<Book> list = bookService.getAllBook();
		return new ModelAndView("bookList","book",list);
	}
	
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		bookService.save(b);
		return "redirect:/available-books";
	}
	
	@GetMapping("/my-books")
	public String getMyBooks(Model model)
	{
		List<MyBookList> list= myBookService.getAllMyBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id) {
		Book b=bookService.getBookById(id);
		MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
		myBookService.saveMyBooks(mb);
		return "redirect:/my-books";
	}
	
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id,Model model) {
		Book b=bookService.getBookById(id);
		model.addAttribute("book",b);
		return "bookEdit";
	}
	
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {
		bookService.deleteById(id);
		return "redirect:/available-books";
	}
	
}
