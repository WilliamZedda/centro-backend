package com.tecnositaf.centrobackend.DTO;

import java.util.Date;

public class AlertDTO {

	private String idAlert;
	private String idDeviceFk;
	private Date timestamp;
	private Integer code;
	private String description;
	private int storageYears;

	public String getIdAlert() {
		return idAlert;
	}

	public void setIdAlert(String idAlert) {
		this.idAlert = idAlert;
	}

	public String getIdDeviceFk() {
		return idDeviceFk;
	}

	public void setIdDeviceFk(String idDeviceFk) {
		this.idDeviceFk = idDeviceFk;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStorageYears() {
		return storageYears;
	}

	public void setStorageYears(int storageYears) {
		this.storageYears = storageYears;
	}
}
