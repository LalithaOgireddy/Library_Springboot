package com.lalitha.library_springboot.repository;

import com.lalitha.library_springboot.entity.BookLoan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookLoanRepository extends CrudRepository<BookLoan, Integer> {

    List<BookLoan> findBookLoansByBorrowerId(Integer borrowerId);

    List<BookLoan> findBookLoansByBook_Id(int bookId);

    List<BookLoan> findBookLoansByReturnedFalse();

    @Query("SELECT bl FROM BookLoan bl WHERE bl.dueDate < CURRENT_DATE AND bl.returned = false")
    List<BookLoan> findBookLoansDue();

    @Query("SELECT bl FROM BookLoan bl WHERE bl.loanDate BETWEEN :startDate AND :endDate")
    List<BookLoan> findBookLoansBetweenDates(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);

    @Modifying
    @Query("UPDATE BookLoan bl SET bl.returned = true WHERE bl.id= :loanId")
    BookLoan markBookLoanAsReturnedByLoanId(@Param("loanId") Integer loanId);
}