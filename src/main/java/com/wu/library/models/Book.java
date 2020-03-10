package com.wu.library.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tbl_book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Book {
    @Id
    @NotNull
    private String id;
    private String title;
    private String publisher;
    private String author;
    private int year;
    private String isbn;
    private int page;
    private String personal;
    private boolean status;
    private String category_id;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
    private Date createDate;

    public Book(String id, String title, String publisher, String author, int year, String isbn, int page, String personal, String category_id) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.page = page;
        this.personal = personal;
        this.category_id = category_id;
    }

    public Book(String title, String publisher, String author, int year, String isbn, int page, String personal) {
        this.title = title;
        this.publisher = publisher;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.page = page;
        this.personal = personal;
    }

    public Book(String title , String id) {
        this.title = title;
        this.id=id;
    }
}
