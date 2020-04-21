package com.tecnositaf.centrobackend.service.alert;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tecnositaf.centrobackend.CentroBackendApplication;
import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.model.Alert;
import com.tecnositaf.centrobackend.service.AlertService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlertServiceTest {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private AlertService alertService;

	@Test
	public void testFalseFindAll() {
		assertSame(94, alertService.findAll().size());
	}

	@Test
	public void testTrueExistById() {
		assertTrue(alertService.existById("5e71e968b2419b6c165251e1"));
	}

	@Test
	public void testValue() {
		List<DTOAlert> alertList = alertService.findAll();
		assertSame(94, alertList.size());
		log.info(alertList.get(0).toString());

		Alert alert = new Alert();
		alert.setIdAlert("5e71e968b2419b6c165251e1");
		alert.setCode(13);
		alert.setDescription("contromano");
		alert.setIdDeviceFk("telecamera");
		alert.setTimestamp(LocalDateTime.of(2000, 12, 12, 11, 10, 10));
		log.info(alert.toDTOAlert().toString());
		assertEquals(alert.toDTOAlert(), alertList.get(0));

	}

	@Test
	public void testGetUsersValueFailureOnInit() {
		List<DTOAlert> usersExpected = Arrays.asList();
		assertNotSame(usersExpected, alertService.findAll());
	}
}
