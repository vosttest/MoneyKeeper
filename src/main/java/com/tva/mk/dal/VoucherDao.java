package com.tva.mk.dal;

import org.springframework.data.repository.CrudRepository;

import com.tva.mk.model.Voucher;

public interface VoucherDao extends CrudRepository<Voucher, Integer> {

}