package com.lalitha.library_springboot.repository;

import com.lalitha.library_springboot.entity.Author;
import com.lalitha.library_springboot.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    Author author1;
    Author author2;
    Author author3;
    Book book1;
    Book book2;

    @BeforeEach
    void setUp() {
        author1 = new Author("Graham","Mitchell");
        author2 = new Author("Samuel","Mitchell");
        author3 = new Author("Graham","Eriksson");
        book1 = new Book("ISBN1","Java",120);
        book2 = new Book("ISBN2","Springboot",120);
        author1.addWrittenBook(book1);
        author2.addWrittenBook(book2);
        author3.addWrittenBook(book1);
        bookRepository.save(book1);
        bookRepository.save(book2);
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
    }
    @Test
    void findByFirstName() {
        Set<Author> authors = authorRepository.findByFirstName("Graham");
        assertEquals(authors.size(),2);
    }

    @Test
    void findByLastName() {
        Set<Author> authors = authorRepository.findByLastName("Mitchell");
        assertTrue(!authors.isEmpty());
    }

    @Test
    void findByFirstNameOrLastName() {
        Set<Author> authors = authorRepository.findByFirstNameOrLastName("Samu");
        assertEquals(authors.size(),1);
    }

    @Test
    void findByWrittenBooks_Id() {
        Set<Author> authors = authorRepository.findByWrittenBooks_Id(book1.getId());
        assertEquals(authors.size(),2);
    }

    @Test
    void updateAuthorNameById() {
        authorRepository.updateAuthorNameById("Sam","Samsson", author2.getId());
        em.flush();
        em.clear();
        Author author = authorRepository.findById(author2.getId()).get();
        assertEquals(author.getFirstName(),"Sam");
        assertEquals(author.getLastName(),"Samsson");
    }

    @Test
    void deleteAuthorById() {
        authorRepository.deleteAuthorById(author3.getId());
        em.flush();
        em.clear();
        Optional<Author> author = authorRepository.findById(author3.getId());
        System.out.println(book1.getAuthors());
        assertTrue(author.isEmpty());
    }
}