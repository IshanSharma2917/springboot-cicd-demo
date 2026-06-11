package com.task.TaskScheduler.Repositiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.TaskScheduler.entity.Task;

public interface TaskRepo extends JpaRepository<Task,Long> {
 
}
