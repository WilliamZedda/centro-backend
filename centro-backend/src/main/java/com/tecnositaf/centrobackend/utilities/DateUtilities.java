package com.tecnositaf.centrobackend.utilities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public final class DateUtilities {

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

	public static LocalDateTime convertDateToLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
}
