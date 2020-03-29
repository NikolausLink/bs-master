package com.example.demo.controller;

import com.example.demo.model.BookEntity;
import com.example.demo.repository.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String bookControllerGetAll(Model model) {
        List<BookEntity> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(value = "/book-search", method = {RequestMethod.POST, RequestMethod.GET})
    public String bookControllerGetAllByTitle(@RequestParam String title, Model model) {
        List<BookEntity> books = bookService.findAllByTitle(title);
        model.addAttribute("books", books);
        return "book-search";
    }


    @RequestMapping(value = "/book-add", method = RequestMethod.GET)
    public String bookFormControllerGet(Model model) {
        model.addAttribute("bookModel", new BookEntity());
        return "book-add";
    }

    @RequestMapping(value = "/book-add", method = RequestMethod.POST)
    public String bookFormControllerPost(@ModelAttribute BookEntity bookModel, Model model) {
        model.addAttribute("bookModel", bookModel);
        bookService.createBook(bookModel);
        return "redirect:/";
    }

    @RequestMapping(value = "/book/{bookId}")
    public String getData(@PathVariable("bookId") Integer bookId, Model model) {
        BookEntity book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "book-details";
    }

}
