package com.fullstack.oblig2.service;

import com.fullstack.oblig2.model.Author;

import java.util.List;


public interface AuthorService {

    Author findAuthorById(Long authorId);

    Author addAuthor(Author author);

    void deleteAuthor(Long authorId);

    Author updateAuthor(Long authorId, Author author);

    List<Author> searchAuthorsByName(String query);

}
