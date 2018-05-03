package com.tva.mk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tva.mk.model.Account;

@Repository
public interface AccountDao extends CrudRepository<Account, Integer> {
//	@Query(nativeQuery = true, value = "SELECT * FROM account a, common_code b WHERE UPPER(a.name) LIKE"
//			+ " UPPER(CONCAT('%', :name, '%')) AND b.id = a.idCode AND a.createBy = :create_by")
//	public List<Account> SearchAccountByIdType(@Param("name") int name, @Param("create_by") int createBy);
//
//	@Query(nativeQuery = true, value = "SELECT * FROM account a, common_code b WHERE a.id = :id AND b.id = a.id_type")
//	public Account getAccountById(@Param("id") int id);
}