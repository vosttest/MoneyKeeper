package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Common;

public interface CommonDao extends CrudRepository<Common, Integer> {
	@Query("FROM Common a WHERE a.type = :type ORDER BY a.sequence")
	public List<Common> getBy(@Param("type") String type);

	@Query(nativeQuery = true, value = "SELECT a.* FROM common a JOIN common b on a.parent_id = b.id WHERE a.type = :type AND b.value = :value ORDER BY a.sequence")
	public List<Common> getBy(@Param("type") String type, @Param("value") String value);

	@Query("FROM Common a WHERE a.type = :type AND a.value = :value")
	public Common getUnique(@Param("type") String type, @Param("value") String value);

	@Query(nativeQuery = true, value = "SELECT MAX(a.sequence) + 1 as sequence FROM common a WHERE a.type = :type")
	public Integer getNextSeq(@Param("type") String type);
}