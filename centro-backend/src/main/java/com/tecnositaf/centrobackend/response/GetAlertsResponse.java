package com.tecnositaf.centrobackend.response;

import java.util.List;
import com.tecnositaf.centrobackend.dto.DTOAlert;

public class GetAlertsResponse extends Response {
	private List<DTOAlert> alerts;
	private Integer numberOfAlerts;

	public GetAlertsResponse(int code, String message, String path, List<DTOAlert> alerts, Integer numberOfAlerts) {
		super(code, message, path);
		this.alerts = alerts;
		this.numberOfAlerts = numberOfAlerts;
	}

	public List<DTOAlert> getAlerts() {
		return alerts;
	}

	public void setAlerts(List<DTOAlert> alerts) {
		this.alerts = alerts;
	}

	public Integer getNumberOfAlerts() {
		return numberOfAlerts;
	}

	public void setNumberOfAlerts(Integer numberOfAlerts) {
		this.numberOfAlerts = numberOfAlerts;
	}
}
