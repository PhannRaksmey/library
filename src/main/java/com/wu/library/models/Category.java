package com.wu.library.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;


@Entity(name = "Category-Entity")
@Table(name = "tbl_category")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Category {

    @Id
    @Column(name = "id")
    private String id;
    private String name;
    private Boolean status;

    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
