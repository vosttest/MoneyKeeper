package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Income;

public interface IncomeDao extends CrudRepository<Income, Integer> {
	@Query("FROM Income a WHERE a.id = :id")
	public Income getBy(@Param("id") int id);

	@Query(nativeQuery = true, value = "SELECT MAX(sequence) + 1 as sequence FROM income WHERE user_id = :userId GROUP BY user_id")
	public int getNextSeq(@Param("userId") int userId);

	@Query("FROM Income a WHERE a.userId = :id AND a.parentId is null order by a.id ASC")
	public List<Income> getParent(@Param("id") int id);

	@Query("FROM Income a WHERE a.userId = :id AND a.parentId is not null order by a.parentId ASC")
	public List<Income> getChild(@Param("id") int id);

	@Query(nativeQuery = true, value = "SELECT SUBSTR(a.code, 4, 2) FROM income a WHERE a.user_id = :userId ORDER BY a.sequence DESC LIMIT 1")
	public int getNextSeqCode(@Param("userId") int userId);
}