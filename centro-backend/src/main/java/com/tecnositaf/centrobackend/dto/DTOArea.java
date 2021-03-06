package com.tecnositaf.centrobackend.dto;

import org.springframework.beans.BeanUtils;
import com.tecnositaf.centrobackend.model.Area;

public class DTOArea {
	private Integer id;
	private String name;
	private String countryCode;
	private String ensignUrl;
	private Integer parentAreaId;
	private String parentArea;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getEnsignUrl() {
		return ensignUrl;
	}

	public void setEnsignUrl(String ensignUrl) {
		this.ensignUrl = ensignUrl;
	}

	public Integer getParentAreaId() {
		return parentAreaId;
	}

	public void setParentAreaId(Integer parentAreaId) {
		this.parentAreaId = parentAreaId;
	}

	public String getParentArea() {
		return parentArea;
	}

	public void setParentArea(String parentArea) {
		this.parentArea = parentArea;
	}
	public Area toArea() {
		Area output = new Area();
		BeanUtils.copyProperties(this, output);
		return output;
	}
}
