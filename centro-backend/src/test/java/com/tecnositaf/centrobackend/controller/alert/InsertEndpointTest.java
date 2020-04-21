package com.tecnositaf.centrobackend.controller.alert;

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

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class InsertEndpointTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wepAppContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
	}

	private final String ENDPOINT_RESOURCE_BASE_URL = "/alerts";

	/********** REQUEST json **********/
	private final String testAlertInsertJSON = "{\"idDeviceFk\":\"telecamera\",\"code\":1,"
			+ "\"description\":\"test\",\"timestamp\":\"2000-10-10 10:10:10\"}";
	private final String testFailureInsertJSON = "{\"idDeviceFk\":null}";

	@Test
	public void successOnInit() throws Exception {		
		mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_RESOURCE_BASE_URL).contentType(MediaType.APPLICATION_JSON)
				.content(testAlertInsertJSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		// response
		.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(0))
		.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("Success"))
		.andExpect(jsonPath("$.numberOfAlerts").exists()).andExpect(jsonPath("$.numberOfAlerts").value(2))
		.andExpect(jsonPath("$.alerts").exists()).andExpect(jsonPath("$.alerts").isArray())
		.andExpect(jsonPath("$.alerts", hasSize(2)))
		// 'alerts'
		.andExpect(jsonPath("$.alerts[1].idAlert").exists())
		.andExpect(jsonPath("$.alerts[1].idDeviceFk").exists())
		.andExpect(jsonPath("$.alerts[1].idDeviceFk").value("telecamera"))
		.andExpect(jsonPath("$.alerts[1].timestamp").exists())
		.andExpect(jsonPath("$.alerts[1].timestamp").value("2000-10-10 10:10:10"))
		.andExpect(jsonPath("$.alerts[1].code").exists())
		.andExpect(jsonPath("$.alerts[1].code").value(1))
		.andExpect(jsonPath("$.alerts[1].description").exists())
		.andExpect(jsonPath("$.alerts[1].description").value("test"))
		.andExpect(jsonPath("$.alerts[1].storageYears").exists())
		.andExpect(jsonPath("$.alerts[1].storageYears").value(20))
		.andDo(print());

	}

	@Test
	public void failureForEmptyField() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT_RESOURCE_BASE_URL).contentType(MediaType.APPLICATION_JSON)
				.content(testFailureInsertJSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
		// response
		.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(-1))
		.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("Failure"))
		.andDo(print());
	}

}
