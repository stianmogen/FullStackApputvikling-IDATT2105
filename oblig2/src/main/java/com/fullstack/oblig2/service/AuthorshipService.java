package com.fullstack.oblig2.service;


import com.fullstack.oblig2.model.Book;

import java.util.List;

public interface AuthorshipService {


    List<Book> getBooksByAuthor(Long authorId);

}
