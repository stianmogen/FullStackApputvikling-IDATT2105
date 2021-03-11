package com.fullstack.oblig2.service;

import com.fullstack.oblig2.model.Author;
import com.fullstack.oblig2.model.Book;
import com.fullstack.oblig2.repo.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


/**
 * Vi skrev enhetstester istedet fordi det gir faen ikke mening å mocke ut den eneste
 * dependencien klassen har og fortsatt kalle det en integrasjonstest.
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    private Book streamerBook;
    private Book chadBibleBook;
    private Author nixolay;
    private Author eirikGangsta;

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepo;
    //findAllByTitleContainingOrAuthors_NameContaining()
    //findAllByTitleContainingAndAuthors_Id()

    @BeforeEach
    public void setUp() {

        streamerBook = new Book(1L, "Nixo Hey", 100, null);
        chadBibleBook = new Book(2L, "Chad Master", 200, null);
        nixolay = new Author(1L, "Nixolay", 270798, null, null);
        eirikGangsta = new Author(2L, "Eirik GangGang", 111099, null, null);

        streamerBook.setAuthors(List.of(nixolay, eirikGangsta));
        chadBibleBook.setAuthors(List.of(eirikGangsta));

        //Er disse overfladisk pga doNuttin() ell my G?
        //bookRepo.saveAndFlush(streamerBook);
        //bookRepo.saveAndFlush(chadBibleBook);

        lenient()
                .when(bookRepo.
                    findAllByTitleContainingOrAuthors_NameContaining("Chad", "Chad"))
                .thenReturn(List.of(chadBibleBook));

        //lenient().doNothing().when(bookRepo).save(chadBibleBook);
        //lenient().doNothing().when(bookRepo).save(streamerBook);
    }

    @Test
    void save() {
        when(bookRepo.save(chadBibleBook)).thenReturn(chadBibleBook);

        Book book = bookService.addBook(chadBibleBook);

        assertThat(book.getId()).isEqualTo(chadBibleBook.getId());
        verify(bookRepo, times(1)).save(chadBibleBook);
    }

    @Test
    void deleteBook(){
        //verifies that bookRepo is used when deleting book through service
        doNothing().when(bookRepo).deleteById(streamerBook.getId());

        bookService.deleteBook(streamerBook.getId());

        verify(bookRepo, times(1))
                .deleteById(streamerBook.getId());
    }

    @Test
    void searchBooks(){
        Mockito.when(bookRepo.findAllByTitleContainingOrAuthors_NameContaining("Chad", "Chad"))
                .thenReturn(List.of(streamerBook, chadBibleBook));

        bookService.searchBooks("Chad");
    }




}