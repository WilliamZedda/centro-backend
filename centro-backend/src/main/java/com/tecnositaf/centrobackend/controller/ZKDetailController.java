package com.tecnositaf.centrobackend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.service.ZKService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
@Controller
public class ZKDetailController {

	private DTOAlert alert = new DTOAlert();

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
		DTOAlert alertSent = (DTOAlert) session.getAttribute("detail");
		this.getAlert().setIdAlert(alertSent.getIdAlert());
		this.getAlert().setIdDeviceFk(alertSent.getIdDeviceFk());
		this.getAlert().setCode(alertSent.getCode());
		this.getAlert().setDescription(alertSent.getDescription());
		this.getAlert().setStorageYears(alertSent.getStorageYears());
		this.getAlert().setTimestamp(alertSent.getTimestamp());
	}

	@Command
	public void goBack() {
		log.info("ZKDetailController: goBack");
		Executions.sendRedirect("~./zul/index2.zul");
	}
}
