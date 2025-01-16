package com.lalitha.library_springboot.repository;

import com.lalitha.library_springboot.entity.AppUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    AppUser findByUsername(String username);
    List<AppUser> findByRegDateBetween(LocalDate startDate,LocalDate endDate);
    AppUser findByUserDetails_Id(Integer id);
    AppUser findByUserDetails_EmailIgnoreCase(String email);
    List<AppUser> findByUserDetails_BirthDate(LocalDate userDetailsBirthDate);
}
