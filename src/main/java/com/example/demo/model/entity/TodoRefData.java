package com.example.demo.model.entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@IdClass(TodoRefData.class)
@Table(name = "TODO_REF")
public class TodoRefData implements Serializable {

    @Id
    @Basic
    @Column(name = "TODO_ID")
    private int toDoId;

    @Id
    @Basic
    @Column(name = "REF_ID")
    private int refId;

    @Basic
    @Column(name = "TODO_YN")
    private String toDoYn;
}
