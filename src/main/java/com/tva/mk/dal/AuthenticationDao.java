package com.tva.mk.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Authentication;

public interface AuthenticationDao extends CrudRepository<Authentication, Integer> {
	/**
	 * Get by client key (for create new authen key) or module (for update authen
	 * key) belong to user.
	 * 
	 * @param clientKey
	 *            client key always have 60 characters
	 * @param module
	 *            module have from 0 to 32 characters
	 * @param userId
	 *            user id
	 * @return
	 */
	@Query("FROM Authentication a WHERE ((a.clientKey = :clientKey AND a.isVerified = FALSE) OR a.module = :module)"
			+ " AND a.createBy = :userId")
	public Authentication getBy(@Param("clientKey") String clientKey, @Param("module") String module,
			@Param("userId") int userId);
}