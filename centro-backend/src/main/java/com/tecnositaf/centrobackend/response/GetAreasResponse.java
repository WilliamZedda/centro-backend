package com.tecnositaf.centrobackend.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tecnositaf.centrobackend.dto.DTOArea;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "count", "filter", "areas" })
public class GetAreasResponse {
	@JsonProperty("count")
	private int count;
	@JsonProperty("filters")
	private Object filter;
	@JsonProperty("areas")
	private List<DTOArea> areas;

	@JsonProperty("areas")
	public List<DTOArea> getAreas() {
		return areas;
	}

	@JsonProperty("areas")
	public void setAreas(List<DTOArea> areas) {
		this.areas = areas;
	}

	@JsonProperty("filters")
	public Object getFilter() {
		return filter;
	}

	@JsonProperty("filters")
	public void setFilter(Object filter) {
		this.filter = filter;
	}

	@JsonProperty("count")
	public int getCount() {
		return count;
	}

	@JsonProperty("count")
	public void setCount(int count) {
		this.count = count;
	}

}