package com.lalitha.library_springboot;

import com.lalitha.library_springboot.entity.*;
import com.lalitha.library_springboot.repository.AppUserRepository;
import com.lalitha.library_springboot.repository.AuthorRepository;
import com.lalitha.library_springboot.repository.BookLoanRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class LibrarySpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibrarySpringbootApplication.class, args);
    }

    /*@Bean
    CommandLineRunner commandLineRunner(AppUserRepository appUserRepository, BookLoanRepository bookLoanRepository, AuthorRepository authorRepository) {
        return args -> {
            Details details = new Details("lalitha@lexicon.com","Lalitha", LocalDate.of(2000,01,01));
            AppUser user1 = new AppUser("Lalitha","Lalitha",LocalDate.now(),details);
            appUserRepository.save(user1);

            Book book1 = new Book("ISBN1", "Java Spring", 120);
            BookLoan bl1 = new BookLoan(LocalDate.now(),LocalDate.of(2025,02,21),false,user1,book1);
            bookLoanRepository.save(bl1);

            Author author = new Author("Graham","Mitchell");
            author.addWrittenBook(book1);
            authorRepository.save(author);

            Author author2 = new Author("Erik","Mitchell");
            author2.addWrittenBook(book1);
            authorRepository.save(author2);


            System.out.println("Book loan created");
            List<BookLoan> bookLoans = bookLoanRepository.findBookLoansDue();
            bookLoans.forEach(System.out::println);

            System.out.println("Book loans update");

            bookLoanRepository.markBookLoanAsReturnedByLoanId(bl1.getId());
            System.out.println(bl1);

            authorRepository.deleteAuthorById(author2.getId());
            System.out.println("After author deletion");
            System.out.println(book1);
            System.out.println(authorRepository.findAll());
        };
    }*/
}
