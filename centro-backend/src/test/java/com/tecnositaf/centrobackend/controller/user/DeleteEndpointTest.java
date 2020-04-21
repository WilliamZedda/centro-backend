package com.tecnositaf.centrobackend.controller.user;

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
public class DeleteEndpointTest {

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
		// XXX il valore dell'id incrementa ad ogni inserimento. quindi 6 non funziona
		// più. al momento è 7 il prossimo valore utile
		mockMvc.perform(
				MockMvcRequestBuilders.delete(ENDPOINT_RESOURCE_BASE_URL + "/6").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// response
				.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(0))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("Success"))
				.andExpect(jsonPath("$.numberOfUsers").exists()).andExpect(jsonPath("$.numberOfUsers").value(3))
				.andExpect(jsonPath("$.users").exists()).andExpect(jsonPath("$.users").isArray())
				.andExpect(jsonPath("$.users", hasSize(3)))
				// 'alerts'
				.andExpect(jsonPath("$.users[0].idUser").exists()).andExpect(jsonPath("$.users[0].idUser").value(1))
				.andExpect(jsonPath("$.users[0].username").exists())
				.andExpect(jsonPath("$.users[0].username").value("b")).andExpect(jsonPath("$.users[0].email").exists())
				.andExpect(jsonPath("$.users[0].email").value("b.b@b.b"))
				.andExpect(jsonPath("$.users[0].password").exists())
				.andExpect(jsonPath("$.users[0].password").value("b"))

				.andExpect(jsonPath("$.users[1].idUser").exists()).andExpect(jsonPath("$.users[1].idUser").value(3))
				.andExpect(jsonPath("$.users[1].username").exists())
				.andExpect(jsonPath("$.users[1].username").value("c")).andExpect(jsonPath("$.users[1].email").exists())
				.andExpect(jsonPath("$.users[1].email").value("b@b.b"))
				.andExpect(jsonPath("$.users[1].password").exists())
				.andExpect(jsonPath("$.users[1].password").value("b"))

				.andExpect(jsonPath("$.users[2].idUser").exists()).andExpect(jsonPath("$.users[2].idUser").value(4))
				.andExpect(jsonPath("$.users[2].username").exists())
				.andExpect(jsonPath("$.users[2].username").value("d")).andExpect(jsonPath("$.users[2].email").exists())
				.andExpect(jsonPath("$.users[2].email").value("b@b.b"))
				.andExpect(jsonPath("$.users[2].password").exists())
				.andExpect(jsonPath("$.users[2].password").value("b"))

				.andDo(print());
	}

	@Test
	public void failureOnInit() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.delete(ENDPOINT_RESOURCE_BASE_URL + "/5").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				// response
				.andExpect(jsonPath("$.code").exists()).andExpect(jsonPath("$.code").value(-1))
				.andExpect(jsonPath("$.message").exists()).andExpect(jsonPath("$.message").value("No Result Found"))
				.andDo(print());
	}
}
