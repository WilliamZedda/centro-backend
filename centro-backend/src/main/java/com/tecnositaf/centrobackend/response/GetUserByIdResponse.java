package com.tecnositaf.centrobackend.response;

import com.tecnositaf.centrobackend.dto.DTOUser;

public class GetUserByIdResponse extends UserResponse {
	private DTOUser user;

	public DTOUser getUser() {
		return user;
	}

	public void setUser(DTOUser user) {
		this.user = user;
	}

	public GetUserByIdResponse(int code, String message, String path, DTOUser user) {
		super(code, message, path);
		this.user = user;
	}
}
