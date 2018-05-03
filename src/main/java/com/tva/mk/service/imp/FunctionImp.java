package com.tva.mk.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dao.FunctionDao;
import com.tva.mk.model.Function;
import com.tva.mk.service.FunctionService;

@Service(value = "functionService")
@Transactional
public class FunctionImp implements FunctionService {
	// region -- Fields --

	@Autowired
	private FunctionDao functionDao;

	// end

	@Override
	public List<Function> search1() {
		List<Function> res = functionDao.search1();
		return res;
	}
}