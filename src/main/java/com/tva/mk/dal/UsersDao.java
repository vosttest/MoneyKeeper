package com.tva.mk.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Users;

public interface UsersDao extends CrudRepository<Users, Integer> {
	@Query("FROM Users a WHERE a.userName = :userName AND a.status = 'ACT' AND a.isDeleted = FALSE")
	public Users getUsersByUserName(@Param("userName") String userName);

	@Query("FROM Users a WHERE (a.userName = :userName OR a.email = :email)"
			+ " AND a.status = 'ACT' AND a.isDeleted = FALSE")
	public Users checkUsersByUserNameOrEmail(@Param("userName") String userName, @Param("email") String email);

	@Query("FROM Users a WHERE (a.userName = :userName OR a.email = :email OR a.accountNo = :accountNo)"
			+ " AND a.status = 'ACT' AND a.isDeleted = FALSE")
	public Users getUsersByUserNameOrEmailOrAccountNo(@Param("userName") String userName, @Param("email") String email,
			@Param("accountNo") String accountNo);

	@Query("FROM Users a WHERE a.id = :id")
	public Users getUsersById(@Param("id") int id);
}