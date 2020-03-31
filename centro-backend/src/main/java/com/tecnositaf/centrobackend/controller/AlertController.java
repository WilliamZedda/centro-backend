package com.tecnositaf.centrobackend.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.response.GetAlertByIdResponse;
import com.tecnositaf.centrobackend.response.GetAlertsResponse;
import com.tecnositaf.centrobackend.response.Response;
import com.tecnositaf.centrobackend.service.AlertService;
import com.tecnositaf.centrobackend.utilities.Common;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/alerts")
public class AlertController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AlertService alertService;

	@PostMapping
	public ResponseEntity<Response> insert(@RequestBody Alert alertToAdd) {
		log.info("addAlert");
		if (!Common.isNull(alertToAdd.getIdAlert()) || Common.alertIsNotCompleteMinusIdAlertCheck(alertToAdd))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		alertService.insert(alertToAdd);
		
		List<Alert> alertList = alertService.findAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
	}

	@GetMapping
	public ResponseEntity<GetAlertsResponse> getAllAlerts() {
		log.info("getAllAlerts");
		List<Alert> alertList = alertService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
	}

	@GetMapping("/{idAlert}")
	public ResponseEntity<Response> getAlert(@PathVariable("idAlert") String idAlert) {
		log.info("getAlert");
		if (Common.idNullOrBlank(idAlert))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		return ResponseEntity.status(HttpStatus.OK)
				.body(new GetAlertByIdResponse(0, Errors.SUCCESS,
						ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
						alertService.findByIdWithStorageYears(idAlert)));
	}

	@DeleteMapping("/{idAlert}")
	public ResponseEntity<Response> deleteAlert(@PathVariable("idAlert") String idAlert) {
		log.info("deleteAlert");
		if (Common.idNullOrBlank(idAlert))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		alertService.deleteById(idAlert);
		List<Alert> alertList = alertService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
	}

	@GetMapping("/device/{idDeviceFk}")
	public ResponseEntity<Response> getAlertByTimestampAndIdDeviceFk(@PathVariable("idDeviceFk") String idDeviceFk,
			@RequestParam(name = "timestamp", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestamp) {
		log.info("getAlertByTimestampAndIdDeviceFk");
		if (Common.idNullOrBlank(idDeviceFk))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		if (Common.isNull(timestamp)) {
			List<Alert> alertList = alertService.findAllByIdDeviceFk(idDeviceFk);
			return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
		} else {
			List<Alert> alertList = alertService.findAllByIdDeviceFkAndTimestamp(idDeviceFk, timestamp);
			return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
		}
	}

	@PutMapping
	public ResponseEntity<Response> update(@RequestBody Alert updateAlert) {
		log.info("updateAlert");
		if (Common.alertIsNotComplete(updateAlert))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		alertService.update(updateAlert);
		List<Alert> alertList = alertService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
	}

	@GetMapping("/storageYears/{storageYears}")
	public ResponseEntity<Response> getAllAlertsEqualToStorageYears(@PathVariable("storageYears") int storageYears) {
		log.info("getAllAlertsEqualToStorageYears");
		if (storageYears < 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		List<Alert> alertList = alertService.findAllByStorageYears(storageYears);
		return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
	}
}
