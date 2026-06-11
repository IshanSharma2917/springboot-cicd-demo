package com.task.TaskScheduler.Repositiory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.TaskScheduler.entity.User;

public interface UserRepo extends JpaRepository<User,Long> {
	
	Optional<User> findByName(String name);
	

}
