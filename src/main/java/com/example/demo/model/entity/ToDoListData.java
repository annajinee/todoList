package com.example.demo.model.entity;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "TODO_LIST")
public class ToDoListData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROWID")
    private Integer rowId;

    @Basic
    @Column(name = "TODO")
    private String toDo;

    @Basic
    @Column(name = "REG_DATE")
    private String regDate;

    @Basic
    @Column(name = "MOD_DATE")
    private String modDate;

    @Basic
    @Column(name = "END_YN")
    private String endYn;

    @Basic
    @Column(name = "REF_IDS")
    private String refIds;

}
