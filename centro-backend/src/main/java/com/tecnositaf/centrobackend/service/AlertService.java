package com.tecnositaf.centrobackend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.FailureException;
//import com.tecnositaf.centrobackend.exception.ResourceNotFoundException;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.repository.AlertRepository;
import com.tecnositaf.centrobackend.utilities.Common;

@Service
public class AlertService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AlertRepository alertRepository;

	public List<Alert> findAll() {
		log.info("findAll");
		List<Alert> alertList = alertRepository.findAll();
		alertList.forEach(alert -> alert.setStorageYears(Common.calculateStorageYears(alert.getTimestamp())));
		return alertList;
	}

	public boolean existById(String idAlert) {
		log.info("existById");
		return alertRepository.existsById(idAlert);
	}

	public Alert insert(Alert alert) {
		log.info("insert");
		return alertRepository.save(alert);
	}

	public Alert update(Alert alert) {
		log.info("update");
		if (!this.existById(alert.getIdAlert()))
//			throw new ResourceNotFoundException();
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		return alertRepository.save(alert);
	}

	public Optional<Alert> findById(String idAlert) {
		log.info("findById");
		return alertRepository.findById(idAlert);
	}

	public Alert findByIdNoOptional(String idAlert) {
		log.info("findByIdNoOptional");
		return findById(idAlert).isPresent() ? alertRepository.findById(idAlert).get() : null;
	}

	public Alert findByIdWithStorageYears(String idAlert) {
		log.info("findByIdWithStorageYears");
		if (!this.existById(idAlert))
			// throw new ResourceNotFoundException();
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		Alert alert = this.findByIdNoOptional(idAlert);
		alert.setStorageYears(Common.calculateStorageYears(alert.getTimestamp()));
		return alert;
	}

	public void deleteById(String idAlert) {
		log.info("deleteById");
		if (!alertRepository.existsById(idAlert))
//			throw new ResourceNotFoundException();
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		alertRepository.deleteById(idAlert);
	}

	public List<Alert> findAllByStorageYears(int storageYears) {
		log.info("findAllByStorageYears");
		List<Alert> alertList = alertRepository.findAll();
		alertList.forEach(alert -> alert.setStorageYears(Common.calculateStorageYears(alert.getTimestamp())));
		List<Alert> filteredList = new ArrayList<>();
		alertList.forEach(alert -> {
			if (alert.getStorageYears() == storageYears)
				filteredList.add(alert);
		});

		return filteredList;
	}

	public List<Alert> findAllByIdDeviceFk(String idDeviceFk) {
		log.info("findAllByIdDeviceFk");
		List<Alert> alertList = alertRepository.findAll();
		alertList.forEach(alert -> alert.setStorageYears(Common.calculateStorageYears(alert.getTimestamp())));
		List<Alert> filteredListByIdDeviceFk = new ArrayList<>();
		alertList.forEach(alert -> {
			if (alert.getIdDeviceFk().equals(idDeviceFk))
				filteredListByIdDeviceFk.add(alert);
		});
		return filteredListByIdDeviceFk;
	}

	public List<Alert> findAllByIdDeviceFkAndTimestamp(String idDeviceFk, LocalDateTime timestamp) {
		log.info("findAllByIdDeviceFkAndTimestamp");
		List<Alert> filteredListByIdDeviceFk = this.findAllByIdDeviceFk(idDeviceFk);
		List<Alert> filteredListByIdDeviceFkAndTimestamp = new ArrayList<>();
		filteredListByIdDeviceFk.forEach(alert -> {
			if (alert.getTimestamp().compareTo(timestamp) >= 0)
				filteredListByIdDeviceFkAndTimestamp.add(alert);
		});
		return filteredListByIdDeviceFkAndTimestamp;
	}
}