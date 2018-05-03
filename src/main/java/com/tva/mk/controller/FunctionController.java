package com.tva.mk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tva.mk.service.FunctionService;

@RestController
@RequestMapping("/Function")
public class FunctionController {
	// region -- Fields --

	@Autowired
	private FunctionService functionService;

	// end

}