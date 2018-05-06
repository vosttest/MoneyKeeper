package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Account;

public interface AccountDao extends CrudRepository<Account, Integer> {
	@Query(nativeQuery = true, value = "SELECT (COUNT(*) + 1) FROM account WHERE user_id = :userId")
	public int getSequenceByUserId(@Param("userId") int userId);

	@Query("FROM Account a WHERE a.id = :id")
	public Account getAccountById(@Param("id") int id);

	@Query("FROM Account a WHERE a.userId = :userId AND a.isDeleted = FALSE AND"
			+ " UPPER(a.text) LIKE CONCAT('%', :keyWord, '%'))")
	public List<Account> searchAccountBelongToUserId(@Param("userId") int userId, @Param("keyWord") String keyWord);
}