package com.tva.mk.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tva.mk.model.Voucher;

public interface VoucherDao extends CrudRepository<Voucher, Integer> {
	@Query("FROM Voucher a WHERE a.id = :id")
	public Voucher getBy(@Param("id") int id);

	@Query(nativeQuery = true, value = "SELECT c.code, c.text, b.amount FROM voucher a\r\n"
			+ "JOIN voucher_detail b ON a.id = b.voucher_id\r\n" + "JOIN expense c ON b.category = c.code\r\n"
			+ "WHERE a.type = 'Expense'\r\n" + "	AND a.account_id in (:accountId)\r\n"
			+ "	AND a.create_on BETWEEN '2018-05-01' AND '2018-05-30';")
	public List<Object[]> getExpense(@Param("accountId") int[] accountId);
}