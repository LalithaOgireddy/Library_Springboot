package com.lalitha.library_springboot.repository;

import com.lalitha.library_springboot.entity.AppUser;
import org.springframework.data.repository.CrudRepository;


public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
}
