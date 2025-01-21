package com.lalitha.library_springboot.repository;

import com.lalitha.library_springboot.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

    Book findByIsbnIgnoreCase(String isbn);
    Book findByTitleContains(String title);
    List<Book> findByMaxLoanDaysLessThan(int maxLoanDays);

}
