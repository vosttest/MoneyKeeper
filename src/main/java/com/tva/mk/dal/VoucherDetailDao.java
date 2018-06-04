package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.VoucherDetail;

public interface VoucherDetailDao extends CrudRepository<VoucherDetail, Integer> {

	@Query("FROM VoucherDetail a WHERE a.voucherId = :voucherId")
	public VoucherDetail getBy(@Param("voucherId") int voucherId);

	@Query(nativeQuery = true, value = "SELECT * FROM getvouchers(:accountId, :voucherId)")
	public List<Object[]> getVouchersDetail(@Param("accountId") int accountId, @Param("voucherId") int voucherId);
}