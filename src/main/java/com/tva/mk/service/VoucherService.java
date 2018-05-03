package com.tva.mk.service;

import java.util.List;

import com.tva.mk.model.Voucher;

public interface VoucherService {
	List<Voucher> getVoucherByCreateBy(Integer createBy);

	List<Voucher> getVoucherByType(String type, Integer createBy);

	List<Voucher> getVoucherByTypeForAdmin(Integer createBy);

	String modify(Voucher v);

	String delete(int id);
}