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

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.response.GetAlertByIdResponse;
import com.tecnositaf.centrobackend.response.GetAlertsResponse;
import com.tecnositaf.centrobackend.response.Response;
import com.tecnositaf.centrobackend.service.AlertService;
import com.tecnositaf.centrobackend.utilities.CheckUtilities;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/alerts")
public class AlertController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AlertService alertService;

	/*
	 * POST
	 */

	@PostMapping
	public ResponseEntity<Response> insert(@RequestBody DTOAlert alert) {
		log.info("insert");
		if (!CheckUtilities.checkDTOAlertInsert(alert)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		}
		alertService.insert(alert.toAlert());
		List<DTOAlert> alertList = alertService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
	}

	/*
	 * PUT
	 */
	@PutMapping
	public ResponseEntity<Response> update(@RequestBody DTOAlert alert) {
		log.info("update");
		if (!CheckUtilities.checkDTOAlert(alert))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		alertService.update(alert.toAlert());
		List<DTOAlert> alertList = alertService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
	}

	/*
	 * DELETE
	 */
	@DeleteMapping("/{idAlert}")
	public ResponseEntity<Response> delete(@PathVariable("idAlert") String idAlert) {
		log.info("delete");
		if (CheckUtilities.isNullOrBlank(idAlert))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		alertService.deleteById(idAlert);
		List<DTOAlert> alertList = alertService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
	}
	/*
	 * GET
	 */

	@GetMapping
	public ResponseEntity<Response> findAll() {
		log.info("findAll");
		List<DTOAlert> alertList = alertService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
	}

	@GetMapping("/{idAlert}")
	public ResponseEntity<Response> findAlert(@PathVariable("idAlert") String idAlert) {
		log.info("findAlert");
		if (CheckUtilities.isNullOrBlank(idAlert))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		return ResponseEntity.status(HttpStatus.OK)
				.body(new GetAlertByIdResponse(0, Errors.SUCCESS,
						ServletUriComponentsBuilder.fromCurrentRequest().toUriString(),
						alertService.findById(idAlert)));
	}

	@GetMapping("/device/{idDeviceFk}")
	public ResponseEntity<Response> findAlertsByTimestampAndIdDeviceFk(@PathVariable("idDeviceFk") String idDeviceFk,
			@RequestParam(name = "timestamp", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime timestamp) {
		log.info("findAlertsByTimestampAndIdDeviceFk");
		if (CheckUtilities.isNullOrBlank(idDeviceFk))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		if (CheckUtilities.isNull(timestamp)) {
			List<DTOAlert> alertList = alertService.findAllByIdDeviceFk(idDeviceFk);
			return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
		} else {
			List<DTOAlert> alertList = alertService.findAllByIdDeviceFkAndTimestamp(idDeviceFk, timestamp);
			return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
		}
	}

	@GetMapping("/storageYears/{storageYears}")
	public ResponseEntity<Response> findAllAlertsEqualToStorageYears(@PathVariable("storageYears") int storageYears) {
		log.info("findAllAlertsEqualToStorageYears");
		if (storageYears < 0)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
					new Response(-1, Errors.FAILURE, ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		List<DTOAlert> alertList = alertService.findAllByStorageYears(storageYears);
		return ResponseEntity.status(HttpStatus.OK).body(new GetAlertsResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), alertList, alertList.size()));
	}
}
