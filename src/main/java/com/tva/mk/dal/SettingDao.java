package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Setting;

public interface SettingDao extends CrudRepository<Setting, Integer> {
	@Query("FROM Setting a WHERE a.id = :id")
	public Setting getBy(@Param("id") int id);

	@Query("FROM Setting a WHERE a.userId = :userId AND a.code = :code")
	public Setting getBy(@Param("userId") int userId, @Param("code") String code);

	@Query("FROM Setting a WHERE a.userId = :userId ORDER BY code ASC")
	public List<Setting> search(@Param("userId") int userId);
	
	@Query(nativeQuery = true, value = "SELECT * FROM set_account_default(:userId)")
	public void setAccountDefault(@Param("userId") int userId);
}