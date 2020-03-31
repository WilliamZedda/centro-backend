package com.tecnositaf.centrobackend.response;

import com.tecnositaf.centrobackend.model.User;

public class GetUserByIdResponse extends UserResponse {
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public GetUserByIdResponse(int code, String message, String path, User user) {
		super(code, message, path);
		this.user = user;
	}
}
