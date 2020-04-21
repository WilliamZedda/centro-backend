package com.tecnositaf.centrobackend.dto;

import java.util.Date;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.utilities.DateUtilities;

public class DTOAlert {

	private String idAlert;
	private String idDeviceFk;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private Date timestamp;
	private Integer code;
	private String description;
	private Integer storageYears;

	public DTOAlert() {
	}

	public DTOAlert(String idAlert, String idDeviceFk, Date timestamp, Integer code, String description,
			int storageYears) {
		this.idAlert = idAlert;
		this.idDeviceFk = idDeviceFk;
		this.timestamp = timestamp;
		this.code = code;
		this.description = description;
		this.storageYears = storageYears;
	}

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

	@Override
	public String toString() {
		return "DTOAlert{" + "idAlert=" + idAlert + ", idDeviceFk='" + idDeviceFk + '\'' + ", description='"
				+ description + '\'' + ", code=" + code + ", timestamp='" + timestamp + '\'' + ", storageYears="
				+ storageYears + '}';
	}

	public Alert toAlert() {
		Alert output = new Alert();
		BeanUtils.copyProperties(this, output);
		output.setTimestamp(DateUtilities.convertDateToLocalDateTime(getTimestamp()));
		return output;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((idAlert == null) ? 0 : idAlert.hashCode());
		result = prime * result + ((idDeviceFk == null) ? 0 : idDeviceFk.hashCode());
		result = prime * result + ((storageYears == null) ? 0 : storageYears.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DTOAlert other = (DTOAlert) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (idAlert == null) {
			if (other.idAlert != null)
				return false;
		} else if (!idAlert.equals(other.idAlert))
			return false;
		if (idDeviceFk == null) {
			if (other.idDeviceFk != null)
				return false;
		} else if (!idDeviceFk.equals(other.idDeviceFk))
			return false;
		if (storageYears == null) {
			if (other.storageYears != null)
				return false;
		} else if (!storageYears.equals(other.storageYears))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		return true;
	}

}
