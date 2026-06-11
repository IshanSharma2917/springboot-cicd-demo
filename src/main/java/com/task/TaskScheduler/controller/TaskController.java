package com.task.TaskScheduler.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.TaskScheduler.Service.TaskService;
import com.task.TaskScheduler.entity.Task;


@RestController
@RequestMapping("/task")
public class TaskController {
	
private final TaskService taskService;
	
	private TaskController(TaskService taskService)
	{
		this.taskService=taskService;
	}
	
	
	@GetMapping("/all")
	public List<Task> getalltask()
	{
		return taskService.getalltask();
	}
	
	@PostMapping("/create")
	public Task createtask(@RequestBody Task task)
	{
		return taskService.create(task);
	}

}
