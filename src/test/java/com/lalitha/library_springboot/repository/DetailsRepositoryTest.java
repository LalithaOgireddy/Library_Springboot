package com.lalitha.library_springboot.repository;

import com.lalitha.library_springboot.entity.Details;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DetailsRepositoryTest {

    @Autowired
    private DetailsRepository detailsRepository;
    Details details = new Details("test@gmail.com","Testname", LocalDate.of(2000,1,1));

    @BeforeEach
    void setUp() {
        Details det = detailsRepository.save(details);
    }

    @Test
    void findDetailsByEmail() {
        Details details1 = detailsRepository.findDetailsByEmail("test@gmail.com");
        assertEquals(details.getEmail(),details1.getEmail());
    }

    @Test
    void findDetailsByNameContains() {
        List<Details> details1 = detailsRepository.findDetailsByNameContains("name");
        assertTrue(details1.contains(details));
    }

    @Test
    void findDetailsByNameIgnoreCase_positive() {
        List<Details> details1 = detailsRepository.findDetailsByNameIgnoreCase("testname");
        assertTrue(details1.contains(details));
    }

    @Test
    void findDetailsByNameIgnoreCase_negative() {
        List<Details> details1 = detailsRepository.findDetailsByNameIgnoreCase("test");
        assertFalse(details1.contains(details));
    }

    @Test
    void findDetailsByBirthDate() {
        List<Details> details1 = detailsRepository.findDetailsByBirthDate(LocalDate.of(2000,1,1));
        assertTrue(details1.contains(details));
    }

    @Test
    void findDetailsByBirthDate_Year() {
        List<Details> details1 = detailsRepository.findDetailsByBirthDate_Year(2000);
        assertTrue(details1.contains(details));
    }

}