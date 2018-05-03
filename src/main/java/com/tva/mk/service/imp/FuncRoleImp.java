package com.tva.mk.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dao.FuncRoleDao;
import com.tva.mk.model.FuncRole;
import com.tva.mk.service.FuncRoleService;

@Service(value = "funcRoleService")
@Transactional
public class FuncRoleImp implements FuncRoleService {
	// region -- Fields --

	@Autowired
	private FuncRoleDao funcRoleDao;

	// end

	@Override
	public List<FuncRole> search1() {
		List<FuncRole> res = funcRoleDao.search1();
		return res;
	}
}