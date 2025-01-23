package com.lalitha.library_springboot.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String isbn;
    @Column(nullable = false)
    private String title;

    private int maxLoanDays;
    private boolean available;

    @ManyToMany(mappedBy = "writtenBooks", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Set<Author> authors = new HashSet<>();;

    protected Book() {}

    public Book(String isbn, String title, int maxLoanDays, boolean available) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
        this.available = available;
    }
    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
        this.available = true;
    }

    public int getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", maxLoanDays=" + maxLoanDays +
                ", authors=" + authors +
                '}';
    }
}
