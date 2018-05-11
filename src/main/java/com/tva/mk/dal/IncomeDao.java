package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Income;

public interface IncomeDao extends CrudRepository<Income, Integer> {
	@Query("FROM Income a WHERE parent_id is null AND userId = :id ORDER BY id ASC")
	public List<Income> getParent(@Param("id") int id);

	@Query("FROM Income a WHERE parent_id is not null AND userId = :id ORDER BY parentId ASC")
	public List<Income> getChild(@Param("id") int id);
	
	@Query("FROM Income a WHERE a.id =:id")
	public Income getBy(@Param("id") int id);
	
	@Query(nativeQuery = true, value = "Select MAX(sequence) + 1 FROM expense a WHERE a.user_id = :id") 
	public int getSequence(@Param("id") int id);
	
	@Query(nativeQuery = true, value = "Select a.code FROM expense a WHERE a.user_id = :id order by a.sequence desc")
	public List<String> getCode(@Param("id") int id);
}