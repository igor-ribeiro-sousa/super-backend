package com.api.rest.controller;

import javax.management.RuntimeErrorException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exception")
public class ExceptionTestController {

	@GetMapping("/500")
	public void RumTimeError() {
		throw new RuntimeErrorException(null, "Simulated 500 for testing");
	}
}
