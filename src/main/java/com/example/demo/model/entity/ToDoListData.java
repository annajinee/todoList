package com.example.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Table(name = "TODO_LIST")
public class ToDoListData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWID")
    private Integer rowId;

    @Basic
    @Column(name = "todo")
    private String todo;

    @Basic
    @Column(name = "REG_DATE")
    private Timestamp regDate;

    @Basic
    @Column(name = "MOD_DATE")
    private Timestamp modDate;

    @Basic
    @Column(name = "END_DATE")
    private Timestamp endDate;

    @Basic
    @Column(name = "END_YN")
    private String endYn;


}
