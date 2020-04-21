package com.tecnositaf.centrobackend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.FailureException;
import com.tecnositaf.centrobackend.model.User;
import com.tecnositaf.centrobackend.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	/*
	 * INSERT
	 */
	public void insert(User user) {
		log.info("insert");
		userRepository.insert(user);
	}

	/*
	 * READ
	 */
	public boolean existById(Integer idUser) {
		log.info("existById");
		long count = userRepository.existById(idUser);
		return count == 1;
	}

	public DTOUser getUserById(Integer idUser) {
		log.info("getUserById");
		if (!existById(idUser))
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		return userRepository.getUserById(idUser).toDTOUser();
	}

	public List<DTOUser> getAll() {
		log.info("getAll");
		List<User> userList = userRepository.getAll();
		List<DTOUser> outpuList = new ArrayList<>();
		userList.forEach(user -> outpuList.add(user.toDTOUser()));
		return outpuList;
	}
	/*
	 * UPDATE
	 */

	public void update(User user) {
		log.info("update");
		if (!existById(user.getIdUser()))
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		userRepository.update(user);
	}

	/*
	 * DELETE
	 */
	public void delete(Integer idUser) {
		log.info("delete");
		if (!existById(idUser))
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		userRepository.delete(idUser);
	}
}
