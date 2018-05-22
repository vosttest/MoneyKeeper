package com.tva.mk.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.TokenAuthentication;

public interface TokenAuthenticationDao extends CrudRepository<TokenAuthentication, Integer> {
	@Query("FROM TokenAuthentication a WHERE a.clientKey = :clientKey AND isVerified = FALSE AND a.userId = :userId")
	public TokenAuthentication getBy(@Param("clientKey") String clientKey, @Param("userId") int userId);
}