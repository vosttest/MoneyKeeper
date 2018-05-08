package com.tva.mk.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

import com.tva.mk.model.Common;

public interface CommonDao extends CrudRepository<Common, Integer> {
	@Query("FROM Common a WHERE a.type = :type")
	public List<Common> getByType(@Param("type") String type);
}