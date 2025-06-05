package com.example.task_management.service;

import com.example.task_management.model.Task;
import com.example.task_management.repository.TaskRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    // Get all tasks
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    // Get task by Id
    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElse(null);
    }

    // Add a new task
    public Task saveTask(Task task){
        return taskRepository.save(task);
    }

    // Update a task
    public Task updateTask(Long id, Task taskDetails){
        Task task = taskRepository.findById(id).orElse(null);
        if(task != null){
            task.setId(taskDetails.getId());
            task.setTitle(taskDetails.getTitle());
            task.setDescription(taskDetails.getDescription());
            task.setCompleted(taskDetails.getStatus());
            task.setCreatedDate(taskDetails.getCreatedDate());
        }
        return null;
    }

    // Delete a task
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }
}
