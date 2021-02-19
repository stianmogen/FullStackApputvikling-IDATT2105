package com.fullstack.oblig2.repo;

import com.fullstack.oblig2.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByTitleContainingOrAuthors_NameContaining(String title, String name);

}
