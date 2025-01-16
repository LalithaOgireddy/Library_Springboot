package com.lalitha.library_springboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class AppUser {

    private int id;
    private String username;
    private String password;
    private LocalDate regDate;

    @OneToOne
    private Details userDetails;


}
