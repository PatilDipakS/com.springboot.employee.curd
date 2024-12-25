package com.blogApplication.api.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApplication.api.config.ModelMapperBean;
import com.blogApplication.api.entities.Users;
import com.blogApplication.api.exception.ResourceNotFoundException;
import com.blogApplication.api.payloads.UsersDto;
import com.blogApplication.api.repository.UserRepo;
import com.blogApplication.api.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UsersDto createUser(UsersDto userDto) {

		Users user = this.userDtoToUser(userDto);
		Users savedUser = this.userRepo.save(user);
		UsersDto userDto1 = this.userToUserDto(savedUser);
		return userDto1;
	}

	@Override
	public UsersDto updateUser(UsersDto userDto, Integer userId) {

		Users user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(user.getAbout());

		Users updatedUser = this.userRepo.save(user);
		UsersDto userDto1 = this.userToUserDto(updatedUser);

		return userDto1;
	}

	@Override
	public UsersDto getUser(Integer userId) {

		Users user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		UsersDto userDto = this.userToUserDto(user);

		return userDto;
	}

	@Override
	public List<UsersDto> getAllUsers() {

		List<Users> users = this.userRepo.findAll();

		// List<UsersDto> userDto = users.stream().map(user ->
		// this.userToUserDto(user)).collect(Collectors.toList());

		List<UsersDto> userDtoList = new ArrayList<>();

		for (Users user : users) {
			UsersDto userDto = this.userToUserDto(user);
			userDtoList.add(userDto);
		}
		return userDtoList;
	}

	@Override
	public void deleteUser(Integer userId) {

		Users user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User ", "Id ", userId));
		this.userRepo.delete(user);

	}

	// Convert method To User To DTO
	public Users userDtoToUser(UsersDto userDto) {

		//Users users = new Users();
		
		Users users = this.modelMapper.map(userDto, Users.class);		
		
		
//		users.setId(userDto.getId());
//		users.setName(userDto.getName());
//		users.setEmail(userDto.getEmail());
//		users.setPassword(userDto.getPassword());
//		users.setAbout(userDto.getAbout());

		return users;

	}

	// Convert method To DTO To User
	public UsersDto userToUserDto(Users users) {

		UsersDto usersDto = this.modelMapper.map(users, UsersDto.class);
//		usersDto.setId(users.getId());
//		usersDto.setName(users.getName());
//		usersDto.setEmail(users.getEmail());
//		usersDto.setPassword(users.getPassword());
//		usersDto.setAbout(users.getAbout());

		return usersDto;

	}

}
