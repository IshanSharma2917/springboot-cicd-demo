package com.task.TaskScheduler.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.TaskScheduler.Service.UserService;
import com.task.TaskScheduler.entity.User;


@RestController
@RequestMapping("/auth")
public class UserController {
private final UserService userService;
	
	private UserController(UserService userService)
	{
		this.userService= userService;
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user)
	{
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user)
	{
		return userService.login(user);
	}

}
