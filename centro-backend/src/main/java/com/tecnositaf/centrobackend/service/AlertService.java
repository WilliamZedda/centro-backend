package com.tecnositaf.centrobackend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
//import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.repository.AlertRepository;
import com.tecnositaf.centrobackend.utilities.DateUtilities;

@Service
public class AlertService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AlertRepository alertRepository;

	/*
	 * INSERT
	 */
	public Alert insert(Alert alert) {
		log.info("insert");
		return alertRepository.save(alert);
	}

	/*
	 * READ
	 */
	public boolean existById(String idAlert) {
		log.info("existById");
		return alertRepository.existsById(idAlert);
	}
// OPZIONALI: ho già il controllo di esistenza quindi fare una nuova ricerca non ha molto senso
//	public Optional<Alert> findByIdOptional(String idAlert) {
//		log.info("findByIdOptional");
//		return alertRepository.findById(idAlert);
//	}
//
//	public DTOAlert findByIdNoOptional(String idAlert) {
//		log.info("findByIdNoOptional");
//		return findByIdOptional(idAlert).isPresent() ? alertRepository.findById(idAlert).get().toDTOAlert() : null;
//	}

	public DTOAlert findById(String idAlert) {
		log.info("findById");
		if (!this.existById(idAlert))
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		return alertRepository.findById(idAlert).get().toDTOAlert();
//		return this.findByIdNoOptional(idAlert);
	}

	public List<DTOAlert> findAll() {
		log.info("findAll");
		List<Alert> alertList = alertRepository.findAll();
		List<DTOAlert> outputList = new ArrayList<>();
		alertList.forEach(alert -> outputList.add(alert.toDTOAlert()));
		return outputList;
	}

	public List<DTOAlert> findAllByStorageYears(int storageYears) {
		log.info("findAllByStorageYears");
		List<DTOAlert> alertList = this.findAll();
		List<DTOAlert> outputList = new ArrayList<>();
		alertList.forEach(alert -> {
			if (alert.getStorageYears() == storageYears) {
				outputList.add(alert);
			}
		});
		return outputList;
	}

	public List<DTOAlert> findAllByIdDeviceFk(String idDeviceFk) {
		log.info("findAllByIdDeviceFk");
		List<DTOAlert> alertList = this.findAll();
		List<DTOAlert> outputList = new ArrayList<>();
		alertList.forEach(alert -> {
			if (alert.getIdDeviceFk().equals(idDeviceFk)) {
				outputList.add(alert);
			}
		});
		return outputList;
	}

	public List<DTOAlert> findAllByIdDeviceFkAndTimestamp(String idDeviceFk, LocalDateTime timestamp) {
		log.info("findAllByIdDeviceFkAndTimestamp");
		List<DTOAlert> alertList = this.findAllByIdDeviceFk(idDeviceFk);
		List<DTOAlert> outputList = new ArrayList<>();
		alertList.forEach(alert -> {
			if (alert.getTimestamp().compareTo(DateUtilities.convertLocalDateTimeToDate(timestamp)) >= 0)
				outputList.add(alert);
		});
		return outputList;
	}

	/*
	 * DELETE
	 */
	public void deleteById(String idAlert) {
		log.info("deleteById");
		if (!alertRepository.existsById(idAlert))
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		alertRepository.deleteById(idAlert);
	}

	/*
	 * UPDATE
	 */

	public Alert update(Alert alert) {
		log.info("update");
		if (!this.existById(alert.getIdAlert()))
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		return alertRepository.save(alert);
	}

}