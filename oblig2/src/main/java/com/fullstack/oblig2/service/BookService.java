package com.fullstack.oblig2.service;

import com.fullstack.oblig2.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    Book addBook(Book book);

    void deleteBook(Long bookId);

    Book updateBook(Long bookId, Book bookUpdated);

    Book findBookByID(Long id);

    List<Book> searchBooks(String name);

}
