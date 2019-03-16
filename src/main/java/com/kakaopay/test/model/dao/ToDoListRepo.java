package com.kakaopay.test.model.dao;

import com.kakaopay.test.model.entity.ToDoListData;
import org.springframework.data.jpa.repository.JpaRepository;
import javax.transaction.Transactional;

@Transactional
public interface ToDoListRepo extends JpaRepository<ToDoListData, Integer>, ToDoListRepoCustom {
    public ToDoListData findByRowId(int rowId);
}
