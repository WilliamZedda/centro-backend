package com.tecnositaf.centrobackend.configuration;

import java.util.List;

import com.tecnositaf.centrobackend.model.Page;

public interface PageConfig {
	public List<Page> getPages();
	public Page getPage(String name);
}
