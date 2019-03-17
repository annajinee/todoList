package com.kakaopay.demo.model.dao;

import com.kakaopay.demo.model.entity.ToDoListData;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;

@Transactional
public interface ToDoListRepo extends JpaRepository<ToDoListData, Integer>, ToDoListRepoCustom {
    public ToDoListData findByRowId(int rowId);
}
