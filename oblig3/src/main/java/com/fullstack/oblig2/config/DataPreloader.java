package com.fullstack.oblig2.config;

import com.fullstack.oblig2.model.Address;
import com.fullstack.oblig2.model.Author;
import com.fullstack.oblig2.model.Book;
import com.fullstack.oblig2.repo.AuthorRepository;
import com.fullstack.oblig2.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataPreloader implements CommandLineRunner {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        Address osloveien = new Address();
        osloveien.setStreet("Osloveien");
        osloveien.setApartmentNumber(1);

        Author hemingway = new Author();
        hemingway.setName("Hemingway");
        hemingway.setBirthdate(1999);
        hemingway.setAddress(osloveien);

        authorRepository.save(hemingway);

        Address lerkendal = new Address();
        lerkendal.setStreet("Lerkendal");
        lerkendal.setApartmentNumber(2);

        Author dostojevski = new Author();
        dostojevski.setName("Fjodor Dostojevski");
        dostojevski.setBirthdate(1857);
        dostojevski.setAddress(lerkendal);

        authorRepository.save(dostojevski);

        Book hemingwaysBook = new Book();
        hemingwaysBook.setPages(499);
        hemingwaysBook.setTitle("For whom the bell tolls");
        hemingwaysBook.setAuthors(List.of(hemingway));

        bookRepository.save(hemingwaysBook);

        Book dostojevskisBook = new Book();
        dostojevskisBook.setPages(299);
        dostojevskisBook.setTitle("Brødrene Karamasov");
        dostojevskisBook.setAuthors(List.of(dostojevski));

        bookRepository.save(dostojevskisBook);
    }
}
