package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Expense;

public interface ExpenseDao extends CrudRepository<Expense, Integer> {
	@Query("FROM Expense a WHERE a.userId = :id AND a.parentId is null order by a.id ASC")
	public List<Expense> getParrent(@Param("id") int id);

	@Query("FROM Expense a WHERE a.userId = :id AND a.parentId is not null order by a.parentId ASC")
	public List<Expense> getChild(@Param("id") int id);
	
	@Query("FROM Expense a WHERE a.id = :id")
	public Expense getBy(@Param("id") int id);
	
	@Query(nativeQuery = true, value = "Select MAX(sequence) + 1 FROM expense a WHERE a.user_id = :id") 
	public int getSequence(@Param("id") int id);
	
	@Query(nativeQuery = true, value = "Select a.code FROM expense a WHERE a.user_id = :id order by a.sequence desc")
	public List<String> getCode(@Param("id") int id);
	
}