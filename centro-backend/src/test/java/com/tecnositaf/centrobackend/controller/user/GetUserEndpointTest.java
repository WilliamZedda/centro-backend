package com.tecnositaf.centrobackend.controller.user;

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
public class GetUserEndpointTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wepAppContext;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wepAppContext).build();
	}

	private final String ENDPOINT_RESOURCE_BASE_URL = "/users";

	@Test
	public void successOnInit() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL+"/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// response
				.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(0))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("Success"))
				.andExpect(jsonPath("$.user").exists())
				// 'alerts'
				.andExpect(jsonPath("$.user.idUser").exists())
				.andExpect(jsonPath("$.user.idUser").value(1))
				.andExpect(jsonPath("$.user.username").exists())
				.andExpect(jsonPath("$.user.username").value("b"))
				.andExpect(jsonPath("$.user.email").exists())
				.andExpect(jsonPath("$.user.email").value("b@b.b"))
				.andExpect(jsonPath("$.user.password").exists())
				.andExpect(jsonPath("$.user.password").value("b"))
				.andDo(print());
	}
	@Test
	public void failureOnInit() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_RESOURCE_BASE_URL+"/14").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		// response
		.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(-1))
		.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("No Result Found"))
		.andDo(print());
	}
}
