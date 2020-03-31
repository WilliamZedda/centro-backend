package com.tecnositaf.centrobackend.response;

import java.util.List;

import com.tecnositaf.centrobackend.model.User;

public class GetUsersResponse extends UserResponse {
	private Integer numberOfUsers;
	private List<User> users;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getNumberOfUsers() {
		return numberOfUsers;
	}

	public void setNumberOfUsers(Integer numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}

	public GetUsersResponse(int code, String message, String path, List<User> users, Integer numberOfUsers) {
		super(code, message, path);
		this.numberOfUsers = numberOfUsers;
		this.users = users;

	}
}
