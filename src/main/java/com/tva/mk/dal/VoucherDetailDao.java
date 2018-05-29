package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.VoucherDetail;

public interface VoucherDetailDao extends CrudRepository<VoucherDetail, Integer> {

	@Query("FROM VoucherDetail a WHERE a.voucherId = :voucherId")
	public List<VoucherDetail> getBy(@Param("voucherId") int voucherId);
}