package com.tecnositaf.centrobackend.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tecnositaf.centrobackend.response.GetAreasResponse;

@Service
public class AreaService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public GetAreasResponse getAllAreas() {
		log.info("getAllAreas");
		RestTemplate restTemplate = new RestTemplate();
		String resourceUrl = "http://api.football-data.org/v2/areas";
		return restTemplate.getForObject(resourceUrl, GetAreasResponse.class);
	}
}
