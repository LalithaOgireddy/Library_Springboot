package com.lalitha.library_springboot.repository;

import com.lalitha.library_springboot.entity.Details;
import org.springframework.data.repository.CrudRepository;

public interface DetailsRepository extends CrudRepository<Details, Integer> {
}
