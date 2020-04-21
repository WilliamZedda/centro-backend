package com.tecnositaf.centrobackend.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.service.ZKService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ZKUpdateController {

	private Date timestamp;
	private DTOAlert alert = new DTOAlert();
	
	public void setTimestamp(Date timestamp) {
		this.timestamp=timestamp;
	}
	public Date getTimestamp() {
		return this.timestamp;
	}

	public DTOAlert getAlert() {
		return alert;
	}

	public void setAlert(DTOAlert alert) {
		this.alert = alert;
	}

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@WireVariable
	ZKService zkService;
	
	@Init
	public void init() {
		
		Session session = Sessions.getCurrent();
		DTOAlert alertSent = (DTOAlert) session.getAttribute("alert");
		this.getAlert().setIdAlert(alertSent.getIdAlert());
		this.getAlert().setIdDeviceFk(alertSent.getIdDeviceFk());
		this.getAlert().setCode(alertSent.getCode());
		this.getAlert().setDescription(alertSent.getDescription());
		this.getAlert().setStorageYears(alertSent.getStorageYears());
		this.getAlert().setTimestamp(alertSent.getTimestamp());
		//this.setTimestamp(Date.from(this.getAlert().getTimestamp().atZone(ZoneId.systemDefault()).toInstant()));

	}
	
	@Command
	public void submit() {
		log.info("ZKUpdateController: submit");		
		//this.getAlert().setTimestamp(LocalDateTime.ofInstant(this.getTimestamp().toInstant(), ZoneId.systemDefault()));
		Alert alert = new Alert();
		alert.setIdAlert(this.getAlert().getIdAlert());
		alert.setIdDeviceFk(this.getAlert().getIdDeviceFk());
		alert.setTimestamp(LocalDateTime.ofInstant(this.getAlert().getTimestamp().toInstant(), ZoneId.systemDefault()));
		alert.setCode(this.alert.getCode());
		alert.setDescription(this.getAlert().getDescription());
		zkService.update(alert);
		Executions.sendRedirect("~./zul/index2.zul");
	}
	@Command
	public void goBack() {
		log.info("ZKUpdateController: goBack");
		Executions.sendRedirect("~./zul/index2.zul");
	}
}


