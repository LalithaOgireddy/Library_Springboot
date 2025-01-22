package com.lalitha.library_springboot.repository;

import com.lalitha.library_springboot.entity.AppUser;
import com.lalitha.library_springboot.entity.Book;
import com.lalitha.library_springboot.entity.BookLoan;
import com.lalitha.library_springboot.entity.Details;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookLoanRepositoryTest {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private EntityManager entityManager;

    Book book;
    Details details;
    AppUser user;
    BookLoan bookLoan;

    @BeforeEach
    void setUp() {
        details = new Details("lalitha@lexicon.com","Lalitha", LocalDate.of(2000,1,1));
        user = new AppUser("Lalitha","Lalitha", LocalDate.now(),details);
        appUserRepository.save(user);
        book = new Book( "ISBN1", "Java Spring", 120);
        bookLoan = new BookLoan(LocalDate.now(),LocalDate.of(2025,1,21),false,user,book);
        bookLoanRepository.save(bookLoan);
    }

    @Test
    void findBookLoansByBorrowerId() {
        List<BookLoan> bookLoans = bookLoanRepository.findBookLoansByBorrowerId(user.getId());
        assertTrue(!bookLoans.isEmpty());
    }

    @Test
    void findBookLoansByBook_Id() {
        List<BookLoan> bookLoans = bookLoanRepository.findBookLoansByBook_Id(book.getId());
        assertTrue(!bookLoans.isEmpty());
    }

    @Test
    void findBookLoansByReturnedFalse() {
        List<BookLoan> bookLoans = bookLoanRepository.findBookLoansByReturnedFalse();
        assertTrue(!bookLoans.isEmpty());
    }

    @Test
    void findBookLoansDue() {
        List<BookLoan> bookLoans = bookLoanRepository.findBookLoansDue();
        assertTrue(!bookLoans.isEmpty());
    }

    @Test
    void findBookLoansBetweenDates() {
        List<BookLoan> bookLoans = bookLoanRepository.findBookLoansBetweenDates(LocalDate.of(2024,1,1),LocalDate.now());
        assertTrue(!bookLoans.isEmpty());
    }

    @Test
    @Transactional
    void markBookLoanAsReturnedByLoanId() {
        bookLoanRepository.markBookLoanAsReturnedByLoanId(bookLoan.getId());
        entityManager.flush();
        entityManager.clear();
        BookLoan updatedLoan = bookLoanRepository.findById(bookLoan.getId()).orElseThrow();
        assertEquals(true, updatedLoan.isReturned());
    }
}