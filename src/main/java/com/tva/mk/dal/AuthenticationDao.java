package com.tva.mk.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Authentication;

public interface AuthenticationDao extends CrudRepository<Authentication, Integer> {
	@Query("FROM Authentication a WHERE a.clientKey = :clientKey AND isVerified = FALSE AND a.createBy = :userId")
	public Authentication getBy(@Param("clientKey") String clientKey, @Param("userId") int userId);
}