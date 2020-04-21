package com.tecnositaf.centrobackend.controller.alert;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
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
public class FindAlertsByTimestampAndIdDeviceFkEndpointTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wepAppContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
	}

	private final String ENDPOINT_RESOURCE_BASE_URL = "/alerts/device";

	@Test
	public void successOnInitDevice() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL + "/websocket")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// response
				.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(0))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("Success"))
				.andExpect(jsonPath("$.numberOfAlerts").exists()).andExpect(jsonPath("$.numberOfAlerts").value(1))
				.andExpect(jsonPath("$.alerts").exists()).andExpect(jsonPath("$.alerts").isArray())
				.andExpect(jsonPath("$.alerts", hasSize(1)))
				// 'alerts'
				.andExpect(jsonPath("$.alerts[0].idAlert").exists())
				.andExpect(jsonPath("$.alerts[0].idAlert").value("5e9ea94ad04c57477db2ba31"))
				.andExpect(jsonPath("$.alerts[0].idDeviceFk").exists())
				.andExpect(jsonPath("$.alerts[0].idDeviceFk").value("websocket"))
				.andExpect(jsonPath("$.alerts[0].timestamp").exists())
				.andExpect(jsonPath("$.alerts[0].timestamp").value("2000-10-10 10:10:10"))
				.andExpect(jsonPath("$.alerts[0].code").exists()).andExpect(jsonPath("$.alerts[0].code").value(1))
				.andExpect(jsonPath("$.alerts[0].description").exists())
				.andExpect(jsonPath("$.alerts[0].description").value("test"))
				.andExpect(jsonPath("$.alerts[0].storageYears").exists())
				.andExpect(jsonPath("$.alerts[0].storageYears").value(20)).andDo(print());
	}

	@Test
	public void successOnInitComplete() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL + "/websocket?1990-12-12%252012:12:12")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// response
				.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(0))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("Success"))
				.andExpect(jsonPath("$.numberOfAlerts").exists()).andExpect(jsonPath("$.numberOfAlerts").value(1))
				.andExpect(jsonPath("$.alerts").exists()).andExpect(jsonPath("$.alerts").isArray())
				.andExpect(jsonPath("$.alerts", hasSize(1)))
				// 'alerts'
				.andExpect(jsonPath("$.alerts[0].idAlert").exists())
				.andExpect(jsonPath("$.alerts[0].idAlert").value("5e9ea94ad04c57477db2ba31"))
				.andExpect(jsonPath("$.alerts[0].idDeviceFk").exists())
				.andExpect(jsonPath("$.alerts[0].idDeviceFk").value("websocket"))
				.andExpect(jsonPath("$.alerts[0].timestamp").exists())
				.andExpect(jsonPath("$.alerts[0].timestamp").value("2000-10-10 10:10:10"))
				.andExpect(jsonPath("$.alerts[0].code").exists()).andExpect(jsonPath("$.alerts[0].code").value(1))
				.andExpect(jsonPath("$.alerts[0].description").exists())
				.andExpect(jsonPath("$.alerts[0].description").value("test"))
				.andExpect(jsonPath("$.alerts[0].storageYears").exists())
				.andExpect(jsonPath("$.alerts[0].storageYears").value(20)).andDo(print());
	}

	@Test
	public void failureOnInit() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL + "/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// response
				.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(-1))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("No Result Found"))
				.andDo(print());
	}
}
