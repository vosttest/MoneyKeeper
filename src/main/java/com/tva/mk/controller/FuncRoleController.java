package com.tva.mk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.service.FuncRoleService;

@RestController
@RequestMapping("/FuncRole")
public class FuncRoleController {
	// region -- Fields --

	@Autowired
	private FuncRoleService funcRoleService;

	// end

}