package com.example.demo.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class ToDoListRepoImpl implements ToDoListRepoCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public int getRowId() {
        Query query = entityManager.createNativeQuery("SELECT LAST_INSERT_ID()");
        Object obj = query.getSingleResult();
        return Integer.parseInt(obj.toString());
    }
}
