package com.fullstack.oblig2.web;


import com.fullstack.oblig2.model.Book;
import com.fullstack.oblig2.service.AuthorshipService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/authors/{authorId}/books")
public class AuthorshipController {

    @Autowired
    private AuthorshipService authorshipService;

    @GetMapping
    public List<Book> getAuthorships(@PathVariable Long authorId) {
        return authorshipService.getBooksByAuthor(authorId);
    }

//    @GetMapping
//    public List<Book> searchBooks(@PathVariable Long authorId, @RequestParam String query) {
//        return authorshipService.searchBooksByAuthor(authorId, query);
//    }

}
