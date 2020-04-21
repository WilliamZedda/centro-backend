package com.tecnositaf.centrobackend.utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.Date;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tecnositaf.centrobackend.CentroBackendApplication;
import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.dto.DTOUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CheckUtilitiesTest {

	@Test
	public void testTrueNullAsValue() {
		assertTrue(CheckUtilities.isNull(null));
	}

	@Test
	public void testTrueEmptyStringAsValue() {
		assertTrue(CheckUtilities.isBlank(""));
	}

	@Test
	public void testFalse() {
		assertFalse(CheckUtilities.isBlank("test"));
	}

	@Test
	public void testTrueIsInteger() {
		assertTrue(CheckUtilities.isInteger(2));
	}

	@Test
	public void testTrueisNullOrBlank() {
		assertTrue(CheckUtilities.isNullOrBlank(" "));
	}

	@Test
	public void testTrueCheckDTOAlert() {
		DTOAlert alert = new DTOAlert();
		alert.setIdAlert("5e71e968b2419b6c165251e1");
		alert.setCode(13);
		alert.setDescription("contromano");
		alert.setIdDeviceFk("telecamera");
		alert.setTimestamp(new Date());

		assertTrue(CheckUtilities.checkDTOAlert(alert));
	}

	@Test
	public void testFalseCheckDTOAlert() {
		DTOAlert alert = new DTOAlert();
		alert.setIdAlert("5e71e968b2419b6c165251e1");
		alert.setCode(13);
		alert.setDescription("contromano");
		alert.setIdDeviceFk("telecamera");
		//alert.setTimestamp(new Date());
		alert.setTimestamp(null);
		assertFalse(CheckUtilities.checkDTOAlert(alert));
	}

	@Test
	public void testTrueCheckDTOAlertInsert() {
		DTOAlert alert = new DTOAlert();
		alert.setCode(13);
		alert.setDescription("contromano");
		alert.setIdDeviceFk("telecamera");
		alert.setTimestamp(new Date());
		
		assertTrue(CheckUtilities.checkDTOAlertInsert(alert));
	}
	
	@Test
	public void testTrueIsUserInser() {
		DTOUser user = new DTOUser();
		user.setEmail("test");
		user.setPassword("password");
		user.setUsername("username");
		assertTrue(CheckUtilities.isUserInsert(user));
	}

	@Test
	public void testTrueIsUserUpdate() {
		DTOUser user = new DTOUser();
		user.setEmail("test");
		user.setPassword("password");
		user.setUsername("username");
		user.setIdUser(1);
		assertTrue(CheckUtilities.isUserUpdate(user));		
	}
}
