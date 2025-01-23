package com.lalitha.library_springboot.repository;

import com.lalitha.library_springboot.entity.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Set<Author> findByFirstName(String firstName);
    Set<Author> findByLastName(String lastName);

    @Query("SELECT a FROM Author a WHERE lower(a.firstName) like concat('%',lower(:name),'%') OR lower(a.lastName) like concat('%',lower(:name),'%')")
    Set<Author> findByFirstNameOrLastName(@Param("name") String name);

    Set<Author> findByWrittenBooks_Id(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE Author a SET a.firstName = :firstName, a.lastName = :lastName WHERE a.id = :id")
    void updateAuthorNameById(@Param("firstName") String firstName,@Param("lastName") String lastName,@Param("id") Integer id);

    @Transactional
    @Modifying
    void deleteAuthorById(int id);
}
