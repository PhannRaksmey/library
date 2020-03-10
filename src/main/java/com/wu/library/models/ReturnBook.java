package com.wu.library.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Table (name = "tbl_return")
@Entity
@Data@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReturnBook {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private String id;
    private String approver;
    private String approverId;
    @OneToOne
    private Book book;
    @OneToOne
    private Member member;

    @Column(name="return_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable=false, updatable=false)
    private Date return_date;
}
