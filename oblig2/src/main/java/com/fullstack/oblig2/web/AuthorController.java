package com.fullstack.oblig2.web;

import com.fullstack.oblig2.model.Author;
import com.fullstack.oblig2.service.AuthorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Author addAuthor(@RequestBody Author author){
        log.debug("[X] Request to add author {}", author);
        return authorService.addAuthor(author);
    }

    @GetMapping("/{authorId}")
    public Author getAuthor(@PathVariable Long authorId) {
        log.debug("[X] Request to delete author with id={}", authorId);
        return authorService.findAuthorById(authorId);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long authorId){
        log.debug("[X] Request to delete author with id={}", authorId);
        authorService.deleteAuthor(authorId);
    }

    @PutMapping("/{authorId}")
    public Author updateAuthor(@RequestBody Author author, @PathVariable Long authorId) {
        log.debug("[X] Request to update author with id={}", authorId);
        return authorService.updateAuthor(authorId, author);
    }

    @GetMapping
    public List<Author> search(@RequestParam String name) {
        log.debug("[X] Request to search authors, name={}", name);
        return authorService.searchAuthorsByName(name);
    }

}
