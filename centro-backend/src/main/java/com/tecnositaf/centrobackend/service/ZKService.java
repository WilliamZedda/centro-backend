package com.tecnositaf.centrobackend.service;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zkoss.zul.ListModelList;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.repository.AlertRepository;

@Service("zkService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ZKService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AlertRepository alertRepository;

	public ListModelList<Alert> findAllModel() {
		log.info("ZKService: findAllModel");
		return new ListModelList<>(this.findAll());
	}

	public List<Alert> findAll() {
		log.info("ZKService: findAll");
		List<Alert> alertList = alertRepository.findAll();
		//alertList.forEach(alert -> alert.setStorageYears(Common.calculateStorageYears(alert.getTimestamp())));
		return alertList;
	}

	public List<DTOAlert> findAllDTO() {
		log.info("ZKService: findAllDTO");
		List<Alert> alertList = alertRepository.findAll();
		List<DTOAlert> alertListDTO = new ArrayList<>();
		for (Alert alert : alertList) {
			DTOAlert el = new DTOAlert();
			el.setIdAlert(alert.getIdAlert());
			el.setIdDeviceFk(alert.getIdDeviceFk());
			el.setCode(alert.getCode());
			el.setDescription(alert.getDescription());
			//el.setStorageYears(alert.getStorageYears());
			el.setTimestamp(Date.from(alert.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()));
			alertListDTO.add(el);
		}
		return alertListDTO;
	}

	public ListModelList<Alert> filterListModel(String filter) {
		log.info("ZKService: filterList");
		List<Alert> list = alertRepository.findAll();
		List<Alert> filteredList = new ArrayList<>();
		for (Alert alert : list) {
			if (alert.getDescription().toLowerCase().contains(filter)
//					||
//					alert.getIdAlert().contains(filter) ||
//					alert.getIdDeviceFk().contains(filter)
			) {
				filteredList.add(alert);
			}
			// manca filtro code, data e storage years
		}
		return new ListModelList<>(filteredList);
	}

	public List<DTOAlert> filterList(String filter) {
		log.info("ZKService: filterList");
		List<Alert> list = alertRepository.findAll();
		List<DTOAlert> filteredList = new ArrayList<>();
		for (Alert alert : list) {
			if (alert.getDescription().toLowerCase().contains(filter.toLowerCase())
//					||
//					alert.getIdAlert().contains(filter) ||
//					alert.getIdDeviceFk().contains(filter)
			) {
				DTOAlert el = new DTOAlert();
				el.setIdAlert(alert.getIdAlert());
				el.setIdDeviceFk(alert.getIdDeviceFk());
				el.setCode(alert.getCode());
				el.setDescription(alert.getDescription());
				//el.setStorageYears(alert.getStorageYears());
				el.setTimestamp(Date.from(alert.getTimestamp().atZone(ZoneId.systemDefault()).toInstant()));
				filteredList.add(el);
			}
			// manca filtro code, data e storage years
		}
		return filteredList;
	}

	public void insert(Alert alert) {
		log.info("ZKService: insert");
		alertRepository.save(alert);
	}

	public void delete(String idAlert) {
		log.info("ZKService: delete");
		if (!alertRepository.existsById(idAlert))
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		alertRepository.deleteById(idAlert);

	}

	public void update(Alert alert) {
		log.info("ZKService: update");
		if (!this.existById(alert.getIdAlert()))
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		alertRepository.save(alert);
	}

	public boolean existById(String idAlert) {
		log.info("ZKService: existById");
		return alertRepository.existsById(idAlert);
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
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		Alert alert = this.findByIdNoOptional(idAlert);
		//alert.setStorageYears(Common.calculateStorageYears(alert.getTimestamp()));
		return alert;
	}

	public void test() {
		log.info("test method");
	}

	/*
	 * public Optional<Alert> findById(String idAlert) { log.info("findById");
	 * return alertRepository.findById(idAlert); }
	 * 
	 * public Alert findByIdNoOptional(String idAlert) {
	 * log.info("findByIdNoOptional"); return findById(idAlert).isPresent() ?
	 * alertRepository.findById(idAlert).get() : null; }
	 * 
	 * public Alert findByIdWithStorageYears(String idAlert) {
	 * log.info("findByIdWithStorageYears"); if (!this.existById(idAlert)) // throw
	 * new ResourceNotFoundException(); throw new
	 * FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND); Alert alert
	 * = this.findByIdNoOptional(idAlert);
	 * alert.setStorageYears(Common.calculateStorageYears(alert.getTimestamp()));
	 * return alert; }
	 * 
	 * 
	 * 
	 * public List<Alert> findAllByStorageYears(int storageYears) {
	 * log.info("findAllByStorageYears"); List<Alert> alertList =
	 * alertRepository.findAll(); alertList.forEach(alert ->
	 * alert.setStorageYears(Common.calculateStorageYears(alert.getTimestamp())));
	 * List<Alert> filteredList = new ArrayList<>(); alertList.forEach(alert -> { if
	 * (alert.getStorageYears() == storageYears) filteredList.add(alert); });
	 * 
	 * return filteredList; }
	 * 
	 * public List<Alert> findAllByIdDeviceFk(String idDeviceFk) {
	 * log.info("findAllByIdDeviceFk"); List<Alert> alertList =
	 * alertRepository.findAll(); alertList.forEach(alert ->
	 * alert.setStorageYears(Common.calculateStorageYears(alert.getTimestamp())));
	 * List<Alert> filteredListByIdDeviceFk = new ArrayList<>();
	 * alertList.forEach(alert -> { if (alert.getIdDeviceFk().equals(idDeviceFk))
	 * filteredListByIdDeviceFk.add(alert); }); return filteredListByIdDeviceFk; }
	 * 
	 * public List<Alert> findAllByIdDeviceFkAndTimestamp(String idDeviceFk,
	 * LocalDateTime timestamp) { log.info("findAllByIdDeviceFkAndTimestamp");
	 * List<Alert> filteredListByIdDeviceFk = this.findAllByIdDeviceFk(idDeviceFk);
	 * List<Alert> filteredListByIdDeviceFkAndTimestamp = new ArrayList<>();
	 * filteredListByIdDeviceFk.forEach(alert -> { if
	 * (alert.getTimestamp().compareTo(timestamp) >= 0)
	 * filteredListByIdDeviceFkAndTimestamp.add(alert); }); return
	 * filteredListByIdDeviceFkAndTimestamp; }
	 */
}
