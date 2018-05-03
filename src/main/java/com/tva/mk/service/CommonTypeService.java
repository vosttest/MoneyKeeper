package com.tva.mk.service;

import java.util.List;

import com.tva.mk.model.CommonType;

public interface CommonTypeService {
	List<CommonType> search1();

	List<CommonType> search2();

	List<CommonType> search3(String name);

	String save(CommonType m);
}