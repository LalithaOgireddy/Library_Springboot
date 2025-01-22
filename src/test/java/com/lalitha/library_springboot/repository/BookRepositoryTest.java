package com.lalitha.library_springboot.repository;

import com.lalitha.library_springboot.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    Book book;

    @BeforeEach
    void setUp() {
        book = new Book("123456","Java Spring JPA",120);
        bookRepository.save(book);
    }
    @Test
    void findByIsbnIgnoreCase() {
        Book b = bookRepository.findByIsbnIgnoreCase("123456");
        assertEquals(b.getIsbn(),"123456");
    }

    @Test
    void findByTitleContains() {
        List<Book> b = bookRepository.findByTitleContains("Java");
        assertNotNull(b);
    }

    @Test
    void findByMaxLoanDaysLessThan_negative() {
        List<Book> b = bookRepository.findByMaxLoanDaysLessThan(100);
        assertEquals(b.size(),0);
    }

    @Test
    void findByMaxLoanDaysLessThan_positive() {
        List<Book> b = bookRepository.findByMaxLoanDaysLessThan(150);
        assertNotNull(b);
    }
}