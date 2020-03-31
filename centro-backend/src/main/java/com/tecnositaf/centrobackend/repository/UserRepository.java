package com.tecnositaf.centrobackend.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tecnositaf.centrobackend.mapper.MapperInterface;
import com.tecnositaf.centrobackend.model.User;

@Repository
public class UserRepository {
	@Autowired
	MapperInterface userMapper;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public List<User> getAllUsers() {
		log.info("getAllUsers");
		return userMapper.getAllUsers();
	}

	public void insertUser(User user) {
		log.info("insertUser");
		userMapper.insertUser(user);

	}

	public User getUserById(Integer idUser) {
		log.info("getUserById");
		return userMapper.getUserById(idUser);
	}

	public void deleteUser(Integer idUser) {
		log.info("deleteUser");
		userMapper.deleteUser(idUser);

	}

	public void updateUser(User updateUser) {
		log.info("updateUser");
		userMapper.updateUser(updateUser);
	}

	public long existById(Integer idUser) {
		log.info("existById");
		return userMapper.existById(idUser);
	}
}
