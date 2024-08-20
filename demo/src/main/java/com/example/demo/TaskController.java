package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final List<Task> tasks = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public List<Task> getAllTasks() {
        return tasks;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        task.setId(counter.incrementAndGet());
        tasks.add(task);
        return task;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setTitle(updatedTask.getTitle());
                task.setCompleted(updatedTask.isCompleted());
                return task;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }
}
