package com.tva.mk.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.common.Utils;
import com.tva.mk.dao.VoucherDetailDao;
import com.tva.mk.dto.VoucherDetailDto;
import com.tva.mk.model.VoucherDetail;
import com.tva.mk.service.VoucherDetailService;

@Service(value = "voucherDetailService")
@Transactional
public class VoucherDetailImpl implements VoucherDetailService {
	// region -- Fields --

	@Autowired
	private VoucherDetailDao dao;

	@PersistenceContext
	private EntityManager entityManager;

	// end

	// region -- Methods --

	@Override
	public String modify(VoucherDetail v) {
		if (v.getId() == null) {
			VoucherDetail res = dao.save(v);
			return res.getId().toString();
		} else {
			VoucherDetail res = dao.getVoucherDetailById(v.getId());
			if (res != null) {
				res = entityManager.merge(v);
				return res.getId().toString();
			}
			return null;
		}
	}

	@Override
	public String delete(int id) {
		VoucherDetail v = dao.getVoucherDetailById(id);
		if (v != null) {
			v.setIsDeleted(true);
			v = entityManager.merge(v);
			return v.getId().toString();
		}
		return null;
	}

	@Override
	public List<VoucherDetailDto> viewVoucherDetailByIdMaster(int idMaster) {
		List<Object[]> tmp = dao.viewVoucherDetailByIdMaster(idMaster);
		List<VoucherDetailDto> res = new ArrayList<>();
		VoucherDetailDto iTmp = new VoucherDetailDto();
		for(Object[] itm : tmp) {
			iTmp.setId(Integer.parseInt(itm[0].toString()));
			iTmp.setIdMaster(Integer.parseInt(itm[1].toString()));
			iTmp.setValue(itm[2].toString());
			iTmp.setAmount(Float.parseFloat(itm[3].toString()));
			iTmp.setIsDeleted(Boolean.parseBoolean(itm[4].toString()));
			iTmp.setCreateBy(Integer.parseInt(itm[5].toString()));
			iTmp.setCreateOn(Utils.dateFormatFromString(itm[6].toString()));
			iTmp.setModifyBy(Integer.parseInt(itm[7].toString()));
			iTmp.setModifyOn(Utils.dateFormatFromString(itm[8].toString()));
			res.add(iTmp);
		}
		return res;
	}

	// end
}