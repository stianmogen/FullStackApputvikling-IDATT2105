package com.fullstack.oblig2.web;

import com.fullstack.oblig2.model.Book;
import com.fullstack.oblig2.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    Logger logger = LoggerFactory.getLogger(AuthorController.class);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book addBook(@RequestBody Book book){
        logger.debug("[X] Request to add book {}", book);
        return this.bookService.addBook(book);
    }

    @GetMapping("/{bookId}")
    public Book findBook(@PathVariable Long bookId){
        logger.debug("[X] Request to find book with id = {}", bookId);
        return this.bookService.findBookByID(bookId);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long bookId){
        logger.debug("[X] Request to delete book with id={}", bookId);
        this.bookService.deleteBook(bookId);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long bookId){
        logger.debug("[X] Request to update Book with id={}", bookId);
        return this.bookService.updateBook(bookId, book);
    }

    @GetMapping
    public List<Book> search(@RequestParam String query) {
        log.debug("[X] Request to search books by name or author, query={}", query);
        return bookService.searchBooks(query);
    }

}
