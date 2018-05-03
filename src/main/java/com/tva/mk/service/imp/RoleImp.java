package com.tva.mk.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dao.RoleDao;
import com.tva.mk.model.Role;
import com.tva.mk.service.RoleService;

@Service(value = "roleService")
@Transactional
public class RoleImp implements RoleService {
	// region -- Fields --

	@Autowired
	private RoleDao roleDao;

	// end

	@Override
	public List<Role> search1() {
		List<Role> res = roleDao.search1();
		return res;
	}
}