package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Income;

public interface IncomeDao extends CrudRepository<Income, Integer> {
	@Query("FROM Income a WHERE parent_id is null AND userId = :id ORDER BY id ASC")
	public List<Income> loadParentIncome(@Param("id") int id);

	@Query("FROM Income a WHERE parent_id is not null AND userId = :id ORDER BY parentId ASC")
	public List<Income> loadChildIncome(@Param("id") int id);
}