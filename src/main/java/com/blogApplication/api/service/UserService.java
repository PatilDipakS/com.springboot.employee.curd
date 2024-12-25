package com.blogApplication.api.service;


import java.util.List;

import com.blogApplication.api.payloads.UsersDto;

public interface UserService {

	public UsersDto createUser(UsersDto userDto);

	public UsersDto updateUser(UsersDto userDto, Integer userId);

	public UsersDto getUser(Integer userId);

	public List<UsersDto> getAllUsers();

	public void deleteUser(Integer userId);

}
