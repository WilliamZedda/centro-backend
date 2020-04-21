package com.tecnositaf.centrobackend.utilities;

import com.tecnositaf.centrobackend.dto.DTOAlert;
import com.tecnositaf.centrobackend.dto.DTOUser;

public class CheckUtilities {

	public static boolean isNull(Object object) {
		return object == null;
	}

	public static boolean isBlank(String string) {
		return string.isBlank();
	}

	public static boolean isNullOrBlank(String id) {
		return isNull(id) || isBlank(id);
	}

	public static boolean isInteger(Integer integer) {
		try {
			Integer.parseInt(integer.toString());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean checkDTOAlert(DTOAlert alert) {
		if (isNull(alert))
			return false;
		if (isNullOrBlank(alert.getIdAlert()))
			return false;
		if (isNullOrBlank(alert.getIdDeviceFk()))
			return false;
		if (isNullOrBlank(alert.getDescription()))
			return false;
		if (isNull(alert.getTimestamp()))
			return false;
		if (!isInteger(alert.getCode()))
			return false;
		return true;
	}

	public static boolean checkDTOAlertInsert(DTOAlert alert) {
		if (isNull(alert))
			return false;
		if (!isNull(alert.getIdAlert()))
			return false;
		if (isNullOrBlank(alert.getIdDeviceFk()))
			return false;
		if (isNullOrBlank(alert.getDescription()))
			return false;
		if (!isInteger(alert.getCode()))
			return false;
		if (isNull(alert.getTimestamp()))
			return false;
		return true;
	}

	public static boolean isUserInsert(DTOUser user) {
		if (isNull(user))
			return false;
		if (isNullOrBlank(user.getUsername()))
			return false;
		if (isNullOrBlank(user.getEmail()))
			return false;
		if (isNullOrBlank(user.getPassword()))
			return false;
		if (!isNull(user.getIdUser()))
			return false;
		return true;
	}

	public static boolean isUserUpdate(DTOUser user) {
		if (isNull(user))
			return false;
		if (isNullOrBlank(user.getUsername()))
			return false;
		if (isNullOrBlank(user.getEmail()))
			return false;
		if (isNullOrBlank(user.getPassword()))
			return false;
		if (isNull(user.getIdUser()))
			return false;
		return true;
	}
}
