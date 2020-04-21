package com.tecnositaf.centrobackend.utilities;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tecnositaf.centrobackend.CentroBackendApplication;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CentroBackendApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DateUtilitiesTest {

	@Test
	public void calculateStorageYearsSuccess() {
		LocalDateTime date = LocalDateTime.of(2000, 10, 10, 10, 10, 10);
		assertSame(20, DateUtilities.calculateStorageYears(date));

	}

	@Test
	public void calculateStorageYearsFailure() {
		LocalDateTime date = LocalDateTime.of(2000, 10, 10, 10, 10, 10);
		assertNotSame(10, DateUtilities.calculateStorageYears(date));

	}
}
