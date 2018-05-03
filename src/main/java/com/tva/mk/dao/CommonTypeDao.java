package com.tva.mk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.CommonType;

public interface CommonTypeDao extends CrudRepository<CommonType, Integer> {
	@Query("FROM CommonType a WHERE a.isDeleted = FALSE")
	public List<CommonType> search1();

	@Query(nativeQuery = true, value = "SELECT * FROM public.common_type WHERE is_deleted = FALSE")
	public List<CommonType> search2();

	@Query("FROM CommonType a WHERE (a.typeName IS NULL OR :name = '' OR UPPER(a.typeName) LIKE CONCAT('%', :name, '%'))")
	public List<CommonType> search3(@Param("name") String name);
}