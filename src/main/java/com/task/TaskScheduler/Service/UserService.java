package com.task.TaskScheduler.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.task.TaskScheduler.Repositiory.UserRepo;
import com.task.TaskScheduler.Security.JwtUtil;
import com.task.TaskScheduler.entity.User;

@Service
public class UserService {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	private final UserRepo userRepo;
	private final PasswordEncoder passwordEncoder;
	
	private UserService(UserRepo userRepo, PasswordEncoder passwordEncoder)
	{
		this.userRepo=userRepo;
		this.passwordEncoder=passwordEncoder;
	}
	
	public ResponseEntity<String> register(User user)
	{
		if(userRepo.findByName(user.getName()).isPresent())
		{
			return ResponseEntity.badRequest().body("UserName already exists");
		}
		
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		userRepo.save(user);
		
		return ResponseEntity.ok("User Registered Successfully");
	}
	
	public ResponseEntity<String> login(User user)
	{
		User user1= userRepo.findByName(user.getName()).orElse(null);
		
		if(user1==null)
		{
			return ResponseEntity.status(401).body("user invalid");
		}
		
		if(!passwordEncoder.matches(user.getPassword(),user1.getPassword()))
		{
			return ResponseEntity.status(401).body("Invalid password");
		}
		
		String token=jwtUtil.generateToken(user.getName());
		
		return ResponseEntity.ok(token);
	}
	

}
