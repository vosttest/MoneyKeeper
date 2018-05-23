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

	@Query(nativeQuery = true, value = "SELECT c.code, c.text, b.amount, a.start_date FROM voucher a\r\n"
			+ "JOIN voucher_detail b ON a.id = b.voucher_id\r\n" + "JOIN expense c ON b.category = c.code\r\n"
			+ "WHERE a.type = 'Expense'\r\n" + " AND a.account_id in (:accountId)\r\n"
			+ "	AND a.start_date BETWEEN :fromDate AND :toDate")
	public List<Object[]> getExpense(@Param("accountId") int[] accountId, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Query(nativeQuery = true, value = "SELECT c.code, c.text, b.amount, a.start_date FROM voucher a\r\n"
			+ "JOIN voucher_detail b ON a.id = b.voucher_id\r\n" + "JOIN income c ON b.category = c.code\r\n"
			+ "WHERE a.type = 'Income'\r\n" + "	AND a.account_id in (:accountId)\r\n"
			+ "	AND a.start_date BETWEEN :fromDate AND :toDate")
	public List<Object[]> getIncome(@Param("accountId") int[] accountId, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	@Query(nativeQuery = true, value = "SELECT id, account_id, type, total, description, payee, payer, to_account, user_id, start_date FROM voucher \r\n"
			+ "WHERE user_id = :userId AND start_date > :date ")
	public List<Object[]> getVoucher(@Param("userId") int userId, @Param("date") Date date);
}