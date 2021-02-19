package com.fullstack.oblig2.service;

import com.fullstack.oblig2.exceptions.AuthorNotFoundException;
import com.fullstack.oblig2.model.Author;
import com.fullstack.oblig2.model.Book;
import com.fullstack.oblig2.repo.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AuthorshipServiceImpl implements AuthorshipService {

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public List<Book> getBooksByAuthor(Long authorId) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(AuthorNotFoundException::new);
        return author.getBooks();
    }
}
