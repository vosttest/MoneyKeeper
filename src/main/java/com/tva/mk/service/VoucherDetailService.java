package com.tva.mk.service;

import java.util.List;

import com.tva.mk.dto.VoucherDetailDto;
import com.tva.mk.model.VoucherDetail;

public interface VoucherDetailService {

	List<VoucherDetailDto> viewVoucherDetailByIdMaster(int idMaster);

	String modify(VoucherDetail v);

	String delete(int id);
}