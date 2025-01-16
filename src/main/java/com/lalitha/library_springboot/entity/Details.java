package com.lalitha.library_springboot.entity;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Details {

    private int id;
    private String email;
    private String name;
    private LocalDate birthDate;
}
