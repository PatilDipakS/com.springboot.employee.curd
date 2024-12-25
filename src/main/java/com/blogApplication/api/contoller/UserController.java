package com.blogApplication.api.contoller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.api.payloads.ApiResponse;
import com.blogApplication.api.payloads.UsersDto;
import com.blogApplication.api.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UsersDto> createdUser(@RequestBody UsersDto userDto) {
		UsersDto createdUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);

	}

	@PutMapping("/{userId}")
	public ResponseEntity<UsersDto> updateUser(@RequestBody UsersDto usersDto, @PathVariable Integer userId) {
		UsersDto updateduser = this.userService.updateUser(usersDto, userId);
		return ResponseEntity.ok(updateduser);

	}

	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId) {

		this.userService.deleteUser(userId);

		// return new ResponseEntity(Map.of("message", "User deleted Successfully"),HttpStatus.OK);
		
		return new ResponseEntity(new ApiResponse("User deleted Successfully", true), HttpStatus.OK);

	}

	@GetMapping("/")
	@ResponseBody
	public ResponseEntity<List<UsersDto>> getallUser() {
		return ResponseEntity.ok(this.userService.getAllUsers());

	}

	@GetMapping("/{userId}")
	@ResponseBody
	public ResponseEntity<UsersDto> getUser(@PathVariable Integer userId) {
		return ResponseEntity.ok(this.userService.getUser(userId));

	}

}
