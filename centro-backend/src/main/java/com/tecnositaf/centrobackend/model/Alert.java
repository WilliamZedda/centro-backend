package com.tecnositaf.centrobackend.model;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.utilities.DateUtilities;

@Document(collection = "alert")
public class Alert {

	@Id
	private String idAlert;
	private String idDeviceFk;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;
	private Integer code;
	private String description;

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

	@Override
	public String toString() {
		return "DTOAlert{" + "idAlert=" + idAlert + ", idDeviceFk='" + idDeviceFk + '\'' + ", description='"
				+ description + '\'' + ", code=" + code + ", timestamp='" + timestamp + '}';
	}

	// XXX nuovo metodo che ho aggiunto al model per il passaggo al DTO
	// corrispondente
	public DTOAlert toDTOAlert() {
		DTOAlert output = new DTOAlert(); // 'this' => User 'output' => DTOUser
		BeanUtils.copyProperties(this, output);
		// vengono settate in 'output' tutti campi che hanno lo stesso nome tra la
		// classe User e DTOUser
		output.setTimestamp(DateUtilities.convertLocalDateTimeToDate(this.getTimestamp()));
		output.setStorageYears(DateUtilities.calculateStorageYears(this.getTimestamp()));
		return output;
	}
}
