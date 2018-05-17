package com.tva.mk.dal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Voucher;

public interface VoucherDao extends CrudRepository<Voucher, Integer> {
	@Query("FROM Voucher a WHERE a.id = :id")
	public Voucher getBy(@Param("id") int id);
}