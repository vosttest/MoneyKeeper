package com.tva.mk.bll;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tva.mk.dal.VoucherDetailDao;
import com.tva.mk.dto.VoucherDetailDto;

@Service(value = "voucherDetailService")
@Transactional
public class VoucherDetailService {
	// region -- Fields --
	@Autowired
	private VoucherDetailDao voucherDetailDao;
	// end

	// region -- Methods --
	public List<VoucherDetailDto> getVouchersDetail(int accountId, int voucherId) {
		List<Object[]> t = voucherDetailDao.getVouchersDetail(accountId, voucherId);
		List<VoucherDetailDto> res = new ArrayList<>();
		for (Object[] item : t) {
			VoucherDetailDto t1 = new VoucherDetailDto();
			t1.setAmount(Double.parseDouble(item[0].toString()));
			t1.setCategoryText(item[1].toString());
			t1.setIcon(item[2].toString());
			t1.setAccountText(item[3].toString());

			res.add(t1);
		}

		return res;
	}
	// end
}