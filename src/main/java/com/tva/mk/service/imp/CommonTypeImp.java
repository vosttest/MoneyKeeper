package com.tva.mk.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dao.CommonTypeDao;
import com.tva.mk.model.CommonType;
import com.tva.mk.service.CommonTypeService;

@Service(value = "commonTypeService")
@Transactional
public class CommonTypeImp implements CommonTypeService {
	// region -- Fields --

	@Autowired
	private CommonTypeDao commonTypeDao;

	// end

	@Override
	public List<CommonType> search1() {
		List<CommonType> res = commonTypeDao.search1();
		return res;
	}

	@Override
	public List<CommonType> search2() {
		List<CommonType> res = commonTypeDao.search2();
		return res;
	}

	@Override
	public List<CommonType> search3(String name) {
		List<CommonType> res = commonTypeDao.search3(name);
		return res;
	}

	@Override
	public String save(CommonType m) {
		CommonType tmp = commonTypeDao.save(m);
		String res = tmp.getId() + "";
		return res;
	}
}