package com.tecnositaf.centrobackend.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.exception.FailureException;
//import com.tecnositaf.centrobackend.exception.ResourceNotFoundException;
import com.tecnositaf.centrobackend.model.User;
import com.tecnositaf.centrobackend.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	public void insertUser(User userToAdd) {
		log.info("insertUser");
		userRepository.insertUser(userToAdd);
	}

	public User getUserById(Integer idUser) {
		log.info("getUserById");
		if (!existById(idUser))
			// throw new ResourceNotFoundException();
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		return userRepository.getUserById(idUser);
	}

	public List<User> getAllUsers() {
		log.info("getAllUsers");
		return userRepository.getAllUsers();
	}

	public void updateUser(User updateUser) {
		log.info("updateUser");
		if (!existById(updateUser.getIdUser()))
//			throw new ResourceNotFoundException();
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		userRepository.updateUser(updateUser);

	}

	public void deleteUser(Integer idUser) {
		log.info("deleteUser");
		if (!existById(idUser))
			// throw new ResourceNotFoundException();
			throw new FailureException(Errors.RESULT_NOT_FOUND, HttpStatus.NOT_FOUND);
		userRepository.deleteUser(idUser);
	}

	public boolean existById(Integer idUser) {
		log.info("existById");
		long count = userRepository.existById(idUser);
		return count == 1;
	}
}
