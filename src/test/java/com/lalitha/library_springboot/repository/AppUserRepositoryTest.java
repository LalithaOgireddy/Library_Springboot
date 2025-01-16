package com.lalitha.library_springboot.repository;

import com.lalitha.library_springboot.entity.AppUser;
import com.lalitha.library_springboot.entity.Details;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AppUserRepositoryTest {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private DetailsRepository detailsRepository;

    Details details = new Details("test@gmail.com","Testname", LocalDate.of(2000,1,1));
    AppUser appUser = new AppUser("testusername","testpassword", LocalDate.now(),details);

    @BeforeEach
    void setUp() {
        Details det = detailsRepository.save(details);
        AppUser users = appUserRepository.save(appUser);
    }

    @Test
    void findByUsername() {
        AppUser user = appUserRepository.findByUsername("testusername");
        assertEquals(user.getUsername(),appUser.getUsername());
    }

    @Test
    void findByRegDateBetween() {
        List<AppUser> userList = appUserRepository.findByRegDateBetween(LocalDate.of(2020,1,1),LocalDate.of(2026,1,1));
        assertEquals(userList.size(),1);
    }

    @Test
    void findByUserDetails_Id() {
        AppUser user = appUserRepository.findByUserDetails_Id(details.getId());
        assertEquals(user.getUserDetails(),appUser.getUserDetails());
    }

    @Test
    void findByUserDetails_EmailIgnoreCase() {
        AppUser user = appUserRepository.findByUserDetails_EmailIgnoreCase("TEST@gmail.com");
        assertEquals(user.getUserDetails().getEmail(),appUser.getUserDetails().getEmail());
    }

    @Test
    void findByUserDetails_BirthDate() {
        List<AppUser> users = appUserRepository.findByUserDetails_BirthDate(LocalDate.of(2000,1,1));
        assertEquals(users.size(),1);
    }
}