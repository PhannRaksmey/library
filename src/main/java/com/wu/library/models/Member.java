package com.wu.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name="Member-Entity")
@Table(name = "tbl_member")

public class Member {
    @Id
    private String id;
    private String name;
    private String sex;
    private String major;
    private int year;
    private String section;
    private String contact;
    private String carrer;
    private String img;
    private boolean status;

    @Column(name="register_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
    private Date date;

    public Member(String id, String name, String sex, String major, int year, String section,String contact, String carrer) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.major = major;
        this.year = year;
        this.section = section;
        this.contact = contact;
        this.carrer = carrer;
    }

    public Member(String name, String sex, String contact) {
        this.name = name;
        this.sex = sex;
        this.contact = contact;
    }

    public Member(String id, String name, String sex, String major, int year, String section, String contact, String carrer, Date date) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.major = major;
        this.year = year;
        this.section = section;
        this.contact = contact;
        this.carrer = carrer;
        this.date = date;
    }

    public Member(String id, String name, String sex, String major, int year, String section, String contact, String carrer, String img, boolean status) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.major = major;
        this.year = year;
        this.section = section;
        this.contact = contact;
        this.carrer = carrer;
        this.img = img;
        this.status = status;
    }
}
