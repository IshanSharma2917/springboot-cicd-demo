package com.task.TaskScheduler.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task.TaskScheduler.Repositiory.TaskRepo;
import com.task.TaskScheduler.entity.Task;

@Service
public class TaskService {
	
	private final TaskRepo taskRepo;
	
	private TaskService(TaskRepo taskRepo)
	{
		this.taskRepo=taskRepo;
	}
	
	public List<Task> getalltask()
	{
		return taskRepo.findAll();
	}
	
	public Task create(Task task)
	{
		return taskRepo.save(task);
	}
	

}
