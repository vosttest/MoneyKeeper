package com.tva.mk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tva.mk.model.VoucherDetail;

@Repository
public interface VoucherDetailDao extends CrudRepository<VoucherDetail, Integer> {
	@Query(nativeQuery = true, value = "SELECT a.id, a.id_master, b.value, a.amount, a.is_deleted,"
			+ " a.create_by, a.create_on, a.modify_by, a.modify_on, a.is_deleted"
			+ " FROM voucher_detail a, common_code b"
			+ " WHERE b.id = a.id_code AND a.id_master = :id_master")
	public List<Object[]> viewVoucherDetailByIdMaster(@Param("id_master") int idMaster);

	@Query("FROM VoucherDetail a WHERE a.id = :id")
	public VoucherDetail getVoucherDetailById(@Param("id") int id);
}