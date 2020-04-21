package com.tecnositaf.centrobackend.controller.alert;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tecnositaf.centrobackend.CentroBackendApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class findAlertEndpointTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wepAppContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
	}

	private final String ENDPOINT_RESOURCE_BASE_URL = "/alerts";

	@Test
	public void successOnInit() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL + "/5e9ea94ad04c57477db2ba31")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// response
				.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(0))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("Success"))
				.andExpect(jsonPath("$.alert").exists())
				// 'alerts'
				.andExpect(jsonPath("$.alert.idAlert").exists())
				.andExpect(jsonPath("$.alert.idAlert").value("5e9ea94ad04c57477db2ba31"))
				.andExpect(jsonPath("$.alert.idDeviceFk").exists())
				.andExpect(jsonPath("$.alert.idDeviceFk").value("websocket"))
				.andExpect(jsonPath("$.alert.timestamp").exists())
				.andExpect(jsonPath("$.alert.timestamp").value("2000-10-10 10:10:10"))
				.andExpect(jsonPath("$.alert.code").exists()).andExpect(jsonPath("$.alert.code").value(1))
				.andExpect(jsonPath("$.alert.description").exists())
				.andExpect(jsonPath("$.alert.description").value("test"))
				.andExpect(jsonPath("$.alert.storageYears").exists())
				.andExpect(jsonPath("$.alert.storageYears").value(20)).andDo(print());
	}
	@Test
	public void failureOnInit() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL + "/test")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(-1))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("No Result Found"))
				
				.andDo(print());
	}
}
