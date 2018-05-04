package com.tva.mk.dal;

import org.springframework.data.repository.CrudRepository;

import com.tva.mk.model.Setting;

public interface SettingDao extends CrudRepository<Setting, Integer> {

}