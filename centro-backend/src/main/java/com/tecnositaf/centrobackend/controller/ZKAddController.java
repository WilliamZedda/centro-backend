package com.tecnositaf.centrobackend.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.tecnositaf.centrobackend.DTO.AlertDTO;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.service.ZKService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ZKAddController {
	
	private AlertDTO alert =new AlertDTO();
	
	public void setAlert(AlertDTO alert) {
		this.alert = alert;
	}
	public AlertDTO getAlert() {
		return this.alert;
	}

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@WireVariable
	ZKService zkService;
	
	@Command
	public void submit() {
		log.info("ZKAddController: submit");
		Alert alertToAdd = new Alert();
		alertToAdd.setIdDeviceFk(this.getAlert().getIdDeviceFk());
		alertToAdd.setTimestamp(LocalDateTime.ofInstant(this.getAlert().getTimestamp().toInstant(), ZoneId.systemDefault()));
		alertToAdd.setCode(this.alert.getCode());
		alertToAdd.setDescription(this.getAlert().getDescription());
		zkService.insert(alertToAdd);
		Executions.sendRedirect("~./zul/index2.zul");
	}
	
	@Command
	public void goBack() {
		log.info("ZKAddController: goBack");
		Executions.sendRedirect("~./zul/index2.zul");
	}
}
