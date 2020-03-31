package com.tecnositaf.centrobackend.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "alert")
public class Alert {

	@Id
	private String idAlert;
	private String idDeviceFk;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;
	private Integer code;
	private String description;
	@Transient
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

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
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
