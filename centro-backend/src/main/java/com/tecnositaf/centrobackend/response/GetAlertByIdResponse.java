package com.tecnositaf.centrobackend.response;

import com.tecnositaf.centrobackend.dto.DTOAlert;

public class GetAlertByIdResponse extends Response {
	private DTOAlert alert;

	public GetAlertByIdResponse(int code, String message, String path, DTOAlert alert) {
		super(code, message, path);
		this.alert = alert;
	}

	public DTOAlert getAlert() {
		return alert;
	}

	public void setAlert(DTOAlert alert) {
		this.alert = alert;
	}
}
