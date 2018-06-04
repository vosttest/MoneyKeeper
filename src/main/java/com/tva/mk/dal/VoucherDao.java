package com.tva.mk.dal;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Voucher;

public interface VoucherDao extends CrudRepository<Voucher, Integer> {
	@Query("FROM Voucher a WHERE a.id = :id AND a.userId = :userId")
	public Voucher getBy(@Param("id") int id, @Param("userId") int userId);

	@Query(nativeQuery = true, value = "SELECT * FROM get_voucher(:keyword, :userId, :date)")
	public List<Object[]> getVoucher(@Param("keyword") String keyword, @Param("userId") int userId,
			@Param("date") Date date);

	@Query(nativeQuery = true, value = "SELECT * FROM get_report(:accountId, :fromDate, :toDate)")
	public List<Object[]> getReports(@Param("accountId") int accountId, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

}