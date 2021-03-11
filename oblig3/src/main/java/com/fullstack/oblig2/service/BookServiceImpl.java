package com.fullstack.oblig2.service;

import com.fullstack.oblig2.exceptions.BookNotFoundException;
import com.fullstack.oblig2.model.Book;
import com.fullstack.oblig2.repo.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book addBook(Book book) {
        return this.bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long bookId) {
        this.bookRepository.deleteById(bookId);
    }

    @Override
    public Book updateBook(Long bookId, Book book) {
        Book updateBook = this.bookRepository.findById(bookId).
                orElseThrow(BookNotFoundException::new);

        updateBook.setTitle(book.getTitle());
        updateBook.setPages(book.getPages());
        updateBook.setAuthors(book.getAuthors());

        return this.bookRepository.save(updateBook);
    }

    @Override
    public Book findBookByID(Long id) {
        return this.bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
    }

    @Override
    public List<Book> searchBooks(String query) {
        log.debug("[X] Searching books. Parameters: query={}", query);
        return bookRepository.findAllByTitleContainingOrAuthors_NameContaining(query, query);
    }

}
