package com.tecnositaf.centrobackend.response;

import java.util.List;

import com.tecnositaf.centrobackend.model.Alert;

public class GetAlertsResponse extends Response {
	private List<Alert> alerts;
	private Integer numberOfAlerts;

	public GetAlertsResponse(int code, String message, String path, List<Alert> alerts, Integer numberOfAlerts) {
		super(code, message, path);
		this.alerts = alerts;
		this.numberOfAlerts = numberOfAlerts;
	}

	public List<Alert> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}

	public Integer getNumberOfAlerts() {
		return numberOfAlerts;
	}

	public void setNumberOfAlerts(Integer numberOfAlerts) {
		this.numberOfAlerts = numberOfAlerts;
	}
}
