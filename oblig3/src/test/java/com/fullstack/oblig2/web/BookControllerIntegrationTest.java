package com.fullstack.oblig2.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstack.oblig2.model.Author;
import com.fullstack.oblig2.model.Book;
import com.fullstack.oblig2.repo.AuthorRepository;
import com.fullstack.oblig2.repo.BookRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = MOCK)
@AutoConfigureMockMvc
class BookControllerIntegrationTest {

    private static final String URI = "/books/";
    public static final int NON_EXISTENT_BOOK_ID = -1;

    @Autowired
    private MockMvc mockMvc; //

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    private Book streamerBook;
    private Book chadBibleBook;
    private Author nixolay;
    private Author eirikGangsta;

    @BeforeEach
    void setUp() {
        streamerBook = new Book();
        streamerBook.setTitle("How to be a streamer");
        streamerBook.setPages(100);
        chadBibleBook = new Book();
        chadBibleBook.setTitle("Chad bible");
        chadBibleBook.setPages(200);

        nixolay = new Author();
        nixolay.setName("Nixolay");
        nixolay.setBirthdate(270798);
        eirikGangsta = new Author();
        eirikGangsta.setName("Eirik Gangsta");
        eirikGangsta.setBirthdate(111099);

        authorRepository.saveAndFlush(nixolay);
        authorRepository.saveAndFlush(eirikGangsta);

        List<Author> streamerBookAuthors = List.of(nixolay, eirikGangsta);
        List<Author> chadBibleAuthors = List.of(eirikGangsta);

        streamerBook.setAuthors(streamerBookAuthors);
        chadBibleBook.setAuthors(chadBibleAuthors);

        bookRepository.saveAndFlush(streamerBook);
        bookRepository.saveAndFlush(chadBibleBook);
    }

    @Test
    void testFindBookWhenBookExistsReturnsBook() throws Exception {
        mockMvc.perform(get(URI + chadBibleBook.getId())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(chadBibleBook.getId()))
                .andExpect(jsonPath("$.title").value(chadBibleBook.getTitle()))
                .andExpect(jsonPath("$.pages").value(chadBibleBook.getPages()))
                .andExpect(jsonPath("$.authors[0].id").value(eirikGangsta.getId()));
    }

    @Test
    void testFindBookWhenBookExistsReturnsStatus200() throws Exception {
        mockMvc.perform(get(URI + chadBibleBook.getId())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testFindBookWhenBookDoesNotExistsReturnsStatus404() throws Exception {
        mockMvc.perform(get(URI + NON_EXISTENT_BOOK_ID)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testAddBookReturnsCreatedBook() throws Exception {
        Book bookToCreate = new Book();
        bookToCreate.setTitle("New book");
        bookToCreate.setPages(200);
        bookToCreate.setAuthors(new ArrayList<>());

        String bookJsonData = objectMapper.writeValueAsString(bookToCreate);

        mockMvc.perform(post(URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookJsonData))
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.title").value(bookToCreate.getTitle()))
                .andExpect(jsonPath("$.pages").value(bookToCreate.getPages()))
                .andExpect(jsonPath("$.authors[*]").isEmpty());
    }

    @Test
    void testAddBookWhenBookDoesNotExistCreatesBook() throws Exception {
        Book bookToCreate = new Book();
        bookToCreate.setTitle("New book");
        bookToCreate.setPages(200);

        String bookJsonData = objectMapper.writeValueAsString(bookToCreate);

        MvcResult result = mockMvc.perform(post(URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookJsonData))
                .andReturn();

        String content = result.getResponse()
                .getContentAsString();
        Long actualBookId = JsonPath.parse(content).read("$.id", Long.class);

        assertTrue(bookRepository.existsById(actualBookId));
    }

    @Test @Disabled("Not implemented")
    void testAddBookWhenBookAlreadyExistsReturnsStatus400() throws Exception {
        String bookJsonData = objectMapper.writeValueAsString(streamerBook);

        mockMvc.perform(post(URI)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookJsonData))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testUpdateBookWhenBookDoesNotExistReturnsStatus400() throws Exception {
        String bookJsonData = objectMapper.writeValueAsString(new Book());

        mockMvc.perform(put(URI + NON_EXISTENT_BOOK_ID)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookJsonData))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateBookReturnsTheUpdatedBook() throws Exception {
        String expectedTitle = "Test update";
        int expectedPages = 1;

        streamerBook.setTitle(expectedTitle);
        streamerBook.setPages(expectedPages);

        String bookJsonData = objectMapper.writeValueAsString(streamerBook);

        mockMvc.perform(put(URI + streamerBook.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookJsonData))
                .andExpect(jsonPath("$.id").value(streamerBook.getId()))
                .andExpect(jsonPath("$.title").value(expectedTitle))
                .andExpect(jsonPath("$.pages").value(expectedPages));
    }

    @Test
    void testUpdateBookReturnsStatus200() throws Exception {
        String bookJsonData = objectMapper.writeValueAsString(streamerBook);

        mockMvc.perform(put(URI + streamerBook.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(bookJsonData))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteBookWhenBookExistsReturnsStatus204() throws Exception {
        Long id = streamerBook.getId();
        mockMvc.perform(delete(URI + id))
                                .andExpect(status().isNoContent());
        assertFalse(bookRepository.existsById(id));
    }
}