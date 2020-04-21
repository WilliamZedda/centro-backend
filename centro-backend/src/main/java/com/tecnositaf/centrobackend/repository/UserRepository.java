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
	/*
	 * READ
	 */

	public List<User> getAll() {
		log.info("getAll");
		return userMapper.getAllUsers();
	}

	public long existById(Integer idUser) {
		log.info("existById");
		return userMapper.existById(idUser);
	}

	public User getUserById(Integer idUser) {
		log.info("getUserById");
		return userMapper.getUserById(idUser);
	}

	/*
	 * INSERT
	 */

	public void insert(User user) {
		log.info("insertUser");
		userMapper.insertUser(user);
	}

	/*
	 * DELETE
	 */

	public void delete(Integer idUser) {
		log.info("deleteUser");
		userMapper.deleteUser(idUser);
	}
	
	/*
	 * UPDATE
	 */

	public void update(User updateUser) {
		log.info("updateUser");
		userMapper.updateUser(updateUser);
	}
}
