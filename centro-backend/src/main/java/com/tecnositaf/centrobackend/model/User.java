package com.tecnositaf.centrobackend.model;

import org.springframework.beans.BeanUtils;

import com.tecnositaf.centrobackend.dto.DTOUser;

public class User {

	private Integer idUser;
	private String username;
	private String email;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + "]";
	}

	public Integer getIdUser() {
		return idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public DTOUser toDTOUser() {
		DTOUser output = new DTOUser();
		BeanUtils.copyProperties(this, output);
		return output;
	}
}
