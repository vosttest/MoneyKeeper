package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Expense;

public interface ExpenseDao extends CrudRepository<Expense, Integer> {
	@Query("FROM Expense a WHERE a.id = :id")
	public Expense getBy(@Param("id") int id);

	@Query(nativeQuery = true, value = "SELECT MAX(sequence) + 1 as sequence FROM expense WHERE user_id = :userId GROUP BY user_id")
	public int getNextSeq(@Param("userId") int userId);

	@Query(nativeQuery = true, value = "SELECT a.id, code, text, parent_id, a.description, (SELECT COUNT(*) FROM Expense b WHERE a.id = b.parent_id AND b.parent_id IS NOT NULL AND b.is_deleted = FALSE), icon FROM Expense a WHERE a.user_id = :id AND a.is_deleted = FALSE AND a.parent_id IS NULL ORDER BY a.id ASC")
	public List<Object[]> getParent(@Param("id") int id);

	@Query(nativeQuery = true, value = "SELECT a.id, code, text, parent_id, a.description, (SELECT COUNT(*) FROM Expense b WHERE a.id = b.parent_id AND b.parent_id IS NOT NULL AND b.is_deleted = FALSE), icon FROM Expense a WHERE a.user_id = :id AND a.is_deleted = FALSE AND a.parent_id IS NOT NULL ORDER BY a.id ASC")
	public List<Object[]> getChild(@Param("id") int id);

	@Query(nativeQuery = true, value = "SELECT SUBSTR(a.code, 4, 2) FROM expense a WHERE a.user_id = :userId ORDER BY a.sequence DESC LIMIT 1")
	public int getNextSeqCode(@Param("userId") int userId);
}