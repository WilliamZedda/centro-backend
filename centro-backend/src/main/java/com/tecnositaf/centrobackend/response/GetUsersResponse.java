package com.tecnositaf.centrobackend.response;

import java.util.List;

import com.tecnositaf.centrobackend.dto.DTOUser;

public class GetUsersResponse extends UserResponse {
	private Integer numberOfUsers;
	private List<DTOUser> users;

	public List<DTOUser> getUsers() {
		return users;
	}

	public void setUsers(List<DTOUser> users) {
		this.users = users;
	}

	public Integer getNumberOfUsers() {
		return numberOfUsers;
	}

	public void setNumberOfUsers(Integer numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}

	public GetUsersResponse(int code, String message, String path, List<DTOUser> users, Integer numberOfUsers) {
		super(code, message, path);
		this.numberOfUsers = numberOfUsers;
		this.users = users;

	}
}
