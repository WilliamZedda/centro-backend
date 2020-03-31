package com.tecnositaf.centrobackend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.model.User;
import com.tecnositaf.centrobackend.response.GetUserByIdResponse;
import com.tecnositaf.centrobackend.response.GetUsersResponse;
import com.tecnositaf.centrobackend.response.UserResponse;
import com.tecnositaf.centrobackend.service.UserService;
import com.tecnositaf.centrobackend.utilities.Common;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@GetMapping
	public ResponseEntity<UserResponse> getAllUsers() {
		log.info("getAllUsers");
		List<User> userList = userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(new GetUsersResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), userList, userList.size()));
	}

	@GetMapping("/{idUser}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable("idUser") Integer idUser) {
		log.info("getUserById");
		if (Common.isNull(idUser) || !Common.isInteger(idUser))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(-1, Errors.FAILURE,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		return ResponseEntity.status(HttpStatus.OK).body(new GetUserByIdResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), userService.getUserById(idUser)));

	}

	@PostMapping
	public ResponseEntity<UserResponse> insertUser(@RequestBody User userToAdd) {
		log.info("insertUser");
		if (!Common.isNull(userToAdd.getIdUser()) || Common.isNull(userToAdd) || Common.isNull(userToAdd.getEmail())
				|| Common.isNull(userToAdd.getUsername()) || Common.isNull(userToAdd.getPassword()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(-1, Errors.FAILURE,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		userService.insertUser(userToAdd);

		List<User> userList = userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(new GetUsersResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), userList, userList.size()));
	}

	@PutMapping
	public ResponseEntity<UserResponse> updateUser(@RequestBody User updateUser) {
		log.info("updateAlert");
		if (Common.isNull(updateUser) || Common.isNull(updateUser.getIdUser()) || Common.isNull(updateUser.getEmail())
				|| Common.isNull(updateUser.getUsername()) || Common.isNull(updateUser.getPassword())
				|| !Common.isInteger(updateUser.getIdUser()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(-1, Errors.FAILURE,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		userService.updateUser(updateUser);
		List<User> userList = userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(new GetUsersResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), userList, userList.size()));
	}

	@DeleteMapping("{idUser}")
	public ResponseEntity<UserResponse> deleteUser(@PathVariable("idUser") Integer idUser) {
		if (Common.isNull(idUser) || !Common.isInteger(idUser))
			return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(-1, Errors.FAILURE,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		userService.deleteUser(idUser);
		List<User> userList = userService.getAllUsers();
		return ResponseEntity.status(HttpStatus.OK).body(new GetUsersResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), userList, userList.size()));
	}
}
