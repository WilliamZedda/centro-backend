package com.tecnositaf.centrobackend.response;

import com.tecnositaf.centrobackend.model.Alert;

public class GetAlertByIdResponse extends Response {
	private Alert alert;

	public GetAlertByIdResponse(int code, String message, String path, Alert alert) {
		super(code, message, path);
		this.alert = alert;
	}

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}
}
