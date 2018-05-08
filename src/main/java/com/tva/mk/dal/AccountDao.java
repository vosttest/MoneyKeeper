package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Account;

public interface AccountDao extends CrudRepository<Account, Integer> {
	@Query("FROM Account a WHERE a.id = :id")
	public Account getBy(@Param("id") int id);

	@Query(nativeQuery = true, value = "SELECT MAX(sequence) + 1 as sequence FROM account WHERE user_id = :userId GROUP BY user_id")
	public int getNextSeq(@Param("userId") int userId);

	@Query(nativeQuery = true, value = "SELECT * FROM account a WHERE a.user_id = :userId AND a.is_deleted = FALSE AND UPPER(a.text) LIKE UPPER(CONCAT('%', :keyword, '%'))")
	public List<Account> search(@Param("userId") int userId, @Param("keyword") String keyword);
}