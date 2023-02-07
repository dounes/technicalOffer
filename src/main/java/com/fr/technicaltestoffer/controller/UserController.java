package com.fr.technicaltestoffer.controller;

import com.fr.technicaltestoffer.aspect.LogExecutionTime;
import com.fr.technicaltestoffer.dto.UserDTO;
import com.fr.technicaltestoffer.entity.User;
import com.fr.technicaltestoffer.exception.UserAlreadyExistException;
import com.fr.technicaltestoffer.exception.UserNotFoundException;
import com.fr.technicaltestoffer.exception.UserNotFrenchOrUnderAgeException;
import com.fr.technicaltestoffer.mapper.UserMapper;
import com.fr.technicaltestoffer.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.LOCATION;

@RestController
@Tag(name = "AF Technical Test Offer")
@RequestMapping("/users")
@Validated
public class UserController {

	IUserService userService;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@Operation(summary = "Get a user by it's id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the user", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid userID"),
			@ApiResponse(responseCode = "404", description = "User not found") })
	@GetMapping("/{userID}")
	@LogExecutionTime
	public ResponseEntity<UserDTO> getUser(@PathVariable Integer userID) throws UserNotFoundException {
		Optional<User> user = userService.getUser(userID);
		if(user.isEmpty()){return ResponseEntity.notFound().build();}
		LOGGER.info("User " + user.get().getFullname() + " born on "+ user.get().getBirthdate() +" is successfully retrieved");
		return  ResponseEntity.ok().body(UserMapper.INSTANCE.mapEntity2DTO(user.get()));
	}

	@Operation(summary = "Create a user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "User created", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = UserDTO.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid Body request"),
			@ApiResponse(responseCode = "409", description = "Conflict : User is already registered") })
	@PostMapping("/")
	@LogExecutionTime
	public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) throws UserAlreadyExistException, UserNotFrenchOrUnderAgeException {
		User user = userService.saveUser(userDTO);
		UserDTO savedUser = UserMapper.INSTANCE.mapEntity2DTO(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/"+savedUser.getUserID())
				.buildAndExpand(savedUser.getUserID())
				.toUri();
		return ResponseEntity.ok().header(LOCATION, String.valueOf(location)).body(savedUser);
	}
}
