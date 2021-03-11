package com.fullstack.oblig2.service;

import com.fullstack.oblig2.exceptions.AuthorAlreadyExistsException;
import com.fullstack.oblig2.exceptions.AuthorNotFoundException;
import com.fullstack.oblig2.model.Author;
import com.fullstack.oblig2.repo.AuthorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    // DI av repo her
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author addAuthor(Author author){
//        authorRepository.findByName(author.getName())
//                .ifPresent(a -> throw new AuthorAlreadyExistsException());

        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long authorId) {
        authorRepository.findById(authorId)
                .orElseThrow(AuthorNotFoundException::new);

        authorRepository.deleteById(authorId);
    }
    @Override
    public Author updateAuthor(Long authorId, Author author) {
        Author authorToUpdate = authorRepository.findById(authorId)
                .orElseThrow(AuthorNotFoundException::new);

        authorToUpdate.setName(author.getName());
        authorToUpdate.setBirthdate(author.getBirthdate());
        authorToUpdate.setBooks(author.getBooks());

        return authorRepository.save(authorToUpdate);
    }

    @Override
    public List<Author> searchAuthorsByName(String name) {
        log.debug("[X] Searching authors. Parameters: name={}", name);
        return authorRepository.findAllByNameContaining(name);
    }

    @Override
    public Author findAuthorById(Long id){
        return authorRepository.findById(id)
                .orElseThrow(AuthorNotFoundException::new);
    }

}
