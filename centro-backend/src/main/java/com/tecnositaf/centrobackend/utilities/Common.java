package com.tecnositaf.centrobackend.utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.tecnositaf.centrobackend.model.Alert;

public final class Common {

	public static LocalDateTime parseStringToDate(String timestamp) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(timestamp, formatter);
	}

	public static int calculateStorageYears(LocalDateTime timestamp) {
		LocalDateTime timestampNow = LocalDateTime.now();
		int storageYears = timestampNow.getYear() - timestamp.getYear();
		if (timestampNow.getDayOfYear() > timestamp.getDayOfYear())
			storageYears++;
		return storageYears;
	}

	public static boolean isInteger(Integer integerToCheck) {
		
		try {
			Integer.parseInt(integerToCheck.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isNull(Object object) {
		return object == null;
	}

	public static boolean isBlank(String string) {
		return string.isBlank();
	}

	public static boolean alertIsNotComplete(Alert alert) {
		return idNullOrBlank(alert.getIdAlert()) || alertIsNotCompleteMinusIdAlertCheck(alert);
	}

	public static boolean alertIsNotCompleteMinusIdAlertCheck(Alert alert) {
		
		return isNull(alert) || idNullOrBlank(alert.getIdDeviceFk()) || isNull(alert.getTimestamp())
				|| isNull(alert.getCode()) || !isInteger(alert.getCode()) || isNull(alert.getDescription());
	}

	public static boolean idNullOrBlank(String id) {
		return isNull(id) || isBlank(id);
	}
	public static boolean alertIsCompleteCheck(Alert alert) {
		return !isNull(alert) && !idNullOrBlank(alert.getIdDeviceFk()) && !isNull(alert.getTimestamp()) 
				&& !isNull(alert.getCode()) && isInteger(alert.getCode()) && !isNull(alert.getDescription());
	}

}
