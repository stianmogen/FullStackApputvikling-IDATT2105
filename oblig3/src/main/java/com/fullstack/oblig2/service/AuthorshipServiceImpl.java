package com.fullstack.oblig2.service;

import com.fullstack.oblig2.exceptions.AuthorNotFoundException;
import com.fullstack.oblig2.model.Author;
import com.fullstack.oblig2.model.Book;
import com.fullstack.oblig2.repo.AuthorRepository;
import com.fullstack.oblig2.repo.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorshipServiceImpl implements AuthorshipService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getBooksByAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(AuthorNotFoundException::new);
        return author.getBooks();
    }

    @Override
    public List<Book> searchBooksByAuthor(Long authorId, String query) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(AuthorNotFoundException::new);

        return bookRepository.findAllByTitleContainingAndAuthors_Id(query, author.getId());
    }
}
