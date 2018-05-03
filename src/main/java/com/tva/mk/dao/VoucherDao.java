package com.tva.mk.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tva.mk.model.Voucher;

@Repository
public interface VoucherDao extends CrudRepository<Voucher, Integer> {
	@Query("FROM Voucher a WHERE a.createBy = :create_by")
	public List<Voucher> getVoucherByCreateBy(@Param("create_by") int createBy);

	@Query("FROM Voucher a WHERE a.createBy = :create_by AND a.type = :type")
	public List<Voucher> getVoucherByType(@Param("type") String type, @Param("create_by") int createBy);
	
	@Query("FROM Voucher a WHERE a.createBy = :create_by")
	public List<Voucher> getVoucherByTypeForAdmin(@Param("create_by") int createBy);
	
	@Query("FROM Voucher a WHERE a.id = :id")
	public Voucher getVoucherById(@Param("id") int id);
}