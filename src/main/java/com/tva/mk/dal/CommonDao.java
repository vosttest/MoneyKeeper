package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Common;

public interface CommonDao extends CrudRepository<Common, Integer> {
	@Query("FROM Common a WHERE a.type = :type")
	public List<Common> getByType(@Param("type") String type);

	@Query(nativeQuery = true, value = "SELECT a.* FROM common a JOIN common b on a.parent_id = b.id WHERE a.type = :type AND b.value = :value")
	public List<Common> getImages(@Param("type") String type, @Param("value") String value);
}