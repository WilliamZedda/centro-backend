package com.tecnositaf.centrobackend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.zkoss.zul.ListModelList;

import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.repository.AlertRepository;

@Service("zkService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ZKService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AlertRepository alertRepository;

	public ListModelList<Alert> findAll() {
		log.info("ZKService: findAll");
		return new ListModelList<>(alertRepository.findAll());
	}

	public ListModelList<Alert> filterList(String filter){
		log.info("ZKService: filterList");
		List<Alert> list = alertRepository.findAll();
		List<Alert> filteredList = new ArrayList<>();
		for (Alert alert : list) {
			if(alert.getDescription().toLowerCase().contains(filter)
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

}
