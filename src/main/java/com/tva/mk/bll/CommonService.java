package com.tva.mk.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.req.BaseReq;
import com.tva.mk.dal.CommonDao;
import com.tva.mk.model.Common;

@Service(value = "commonService")
@Transactional
public class CommonService {
	// region -- Fields --

	@Autowired
	private CommonDao commonDao;

	// end

	// region -- Methods --

	public List<Common> getByType(String type) {
		List<Common> res = commonDao.getByType(type);
		return res;
	}

	// end
}