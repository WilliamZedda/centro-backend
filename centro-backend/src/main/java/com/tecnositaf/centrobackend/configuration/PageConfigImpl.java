package com.tecnositaf.centrobackend.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.tecnositaf.centrobackend.model.Page;

public class PageConfigImpl {
	HashMap<String,Page> pageMap = new LinkedHashMap<>();
	public PageConfigImpl() {
		pageMap.put("test", new Page("test","Add","","/web/zul/add_alert.zul"));
	}
	
	public List<Page> getPages(){
		return new ArrayList<>(pageMap.values());
	}
	
	public Page getPage(String name){
		return pageMap.get(name);
	}
}
