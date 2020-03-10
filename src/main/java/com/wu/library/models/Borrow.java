package com.wu.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tbl_borrow")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Borrow {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String approver;
    private String approverId;

    @OneToOne
    private Book book;
    @OneToOne
    private Member member;

    @Column(name="borrow_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
    private Date borrow_date;


}
