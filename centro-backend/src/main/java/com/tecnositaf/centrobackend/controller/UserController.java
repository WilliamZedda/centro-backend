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

import com.tecnositaf.centrobackend.dto.DTOUser;
import com.tecnositaf.centrobackend.enumeration.Errors;
import com.tecnositaf.centrobackend.response.GetUserByIdResponse;
import com.tecnositaf.centrobackend.response.GetUsersResponse;
import com.tecnositaf.centrobackend.response.UserResponse;
import com.tecnositaf.centrobackend.service.UserService;
import com.tecnositaf.centrobackend.utilities.CheckUtilities;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	UserService userService;
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	/*
	 * GET
	 */

	@GetMapping
	public ResponseEntity<UserResponse> getAll() {
		log.info("getAll");
		List<DTOUser> userList = userService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(new GetUsersResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), userList, userList.size()));
	}

	@GetMapping("/{idUser}")
	public ResponseEntity<UserResponse> getUserById(@PathVariable("idUser") Integer idUser) {
		log.info("getUserById");
		if (CheckUtilities.isNull(idUser))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(-1, Errors.FAILURE,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		return ResponseEntity.status(HttpStatus.OK).body(new GetUserByIdResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), userService.getUserById(idUser)));
	}

	/*
	 * POST
	 */
	@PostMapping
	public ResponseEntity<UserResponse> insert(@RequestBody DTOUser user) {
		log.info("insertUser");
		if (!CheckUtilities.isUserInsert(user))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(-1, Errors.FAILURE,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		
		userService.insert(user.toUser());
		
		List<DTOUser> userList = userService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(new GetUsersResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), userList, userList.size()));
	}
	/*
	 * PUT
	 */

	@PutMapping
	public ResponseEntity<UserResponse> update(@RequestBody DTOUser user) {
		log.info("updateAlert");
		if (!CheckUtilities.isUserUpdate(user))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(-1, Errors.FAILURE,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));

		userService.update(user.toUser());
		
		List<DTOUser> userList = userService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(new GetUsersResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), userList, userList.size()));
	}

	/*
	 * DELETE
	 */
	@DeleteMapping("/{idUser}")
	public ResponseEntity<UserResponse> delete(@PathVariable("idUser") Integer idUser) {
		if (CheckUtilities.isNull(idUser))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UserResponse(-1, Errors.FAILURE,
					ServletUriComponentsBuilder.fromCurrentRequest().toUriString()));
		userService.delete(idUser);
		List<DTOUser> userList = userService.getAll();
		return ResponseEntity.status(HttpStatus.OK).body(new GetUsersResponse(0, Errors.SUCCESS,
				ServletUriComponentsBuilder.fromCurrentRequest().toUriString(), userList, userList.size()));
	}
}
