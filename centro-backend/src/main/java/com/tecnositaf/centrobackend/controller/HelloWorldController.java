package com.tecnositaf.centrobackend.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/time/now")
	public String getLocalDateTime() {
		// return la data+ora corrente sotto forma di stringa umanamente leggibile
		String DATEFORMATTER = "yyyy-MM-dd HH:mm:ss";
		LocalDateTime dateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATEFORMATTER);
		return dateTime.format(formatter);
	}

	@GetMapping("/list/random")
	public List<Integer> generateRandomNumberList() {
		// return una lista di 10 numeri generati random con range [0,100]
		ArrayList<Integer> randomNumberList = new ArrayList<>();
		int limitValue = 100;
		for (int i = 0; i < 10; i++) {
			randomNumberList.add((int) Math.floor(Math.random() * (limitValue + 1)));
		}
		return randomNumberList;
	}
}