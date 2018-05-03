package com.tva.mk.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dao.UsersDao;
import com.tva.mk.model.Users;
import com.tva.mk.service.UsersService;

@Service(value = "usersService")
@Transactional
public class UsersImp implements UsersService {
	// region -- Fields --

	@Autowired
	private UsersDao usersDao;

	// end

	@Override
	public List<Users> search1() {
		List<Users> res = usersDao.search1();
		return res;
	}
}