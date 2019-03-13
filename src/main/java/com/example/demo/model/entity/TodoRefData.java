package com.example.demo.model.entity;

import lombok.Data;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "TODO_REF")
public class TodoRefData {

    @Basic
    @Column(name = "TODO_ID")
    private int todoId;

    @Basic
    @Column(name = "REF_ID")
    private int refId;
}
