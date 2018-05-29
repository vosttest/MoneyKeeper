package com.tva.mk.dal;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Voucher;

public interface VoucherDao extends CrudRepository<Voucher, Integer> {
	@Query("FROM Voucher a WHERE a.id = :id")
	public Voucher getBy(@Param("id") int id);
	
	@Query(nativeQuery = true, value = "SELECT id, account_id, type, total, description, payee, payer, to_account, user_id, start_date FROM voucher WHERE user_id = :id AND start_date >= :date")
	public List<Object[]> getVoucher(@Param("id") int id, @Param("date") Date date);
	
//	@Query("FROM Voucher a WHERE a.userId = :userId AND a.startDate >= :date")
//	public List<Object[]> getVoucher(@Param("userId") int userId, @Param("date") Date date);

	@Query(nativeQuery = true, value = "SELECT * FROM getReports(:accountId, :fromDate, :toDate)")
	public List<Object[]> getReports(@Param("accountId") int accountId, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);
}