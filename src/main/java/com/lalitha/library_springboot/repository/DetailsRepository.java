package com.lalitha.library_springboot.repository;

import com.lalitha.library_springboot.entity.Details;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer> {
    Details findDetailsByEmail(String email);
    List<Details> findDetailsByNameContains(String name);
    List<Details> findDetailsByNameIgnoreCase(String name);
    List<Details> findDetailsByBirthDate(LocalDate birthDate);
    @Query("SELECT d from Details  d where YEAR(d.birthDate) = :year")
    List<Details> findDetailsByBirthDate_Year(@Param("year") int year);
}
