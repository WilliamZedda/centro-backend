package com.tecnositaf.centrobackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tecnositaf.centrobackend.response.GetAreasResponse;
import com.tecnositaf.centrobackend.service.AreaService;

@RestController
public class AreaController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AreaService areaService;

	@GetMapping("/consume/areas")
	public ResponseEntity<GetAreasResponse> getAllAreas() {
		log.info("getAllAreas");
		return ResponseEntity.status(HttpStatus.OK).body(areaService.getAllAreas());
	}
}
