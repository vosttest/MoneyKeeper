package com.tva.mk.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tva.mk.model.CommonCode;

@Repository
public interface CommonCodeDao extends CrudRepository<CommonCode, Integer> {
	// @Query(nativeQuery= true, value = "SELECT * FROM common_code a, common_type b
	// WHERE a.id_type ="
	// + " :id_type AND b.id = a.id_type")
	// public List<CommonCode> ViewCommonCodeByIdType(@Param("id_type") int idType);
	//
	// @Query(nativeQuery= true, value="SELECT * FROM common_code a, common_type b
	// WHERE"
	// + " a.id = :id AND b.id = a.id_type")
	// public CommonCode getCommonCodeById(@Param("id") int id);
}