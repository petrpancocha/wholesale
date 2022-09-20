package com.petrpancocha.wholesale.controller;

import com.petrpancocha.wholesale.dto.TaskCreateDto;
import com.petrpancocha.wholesale.dto.TaskDto;
import com.petrpancocha.wholesale.dto.TaskUpdateDto;
import com.petrpancocha.wholesale.model.Task;
import com.petrpancocha.wholesale.repository.TaskMyBatisRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@Api(value = "Tasks API")
public class TaskController {
    @Autowired
    private TaskMyBatisRepository taskRepository;

    @GetMapping
    public List<TaskDto> getTasks(@RequestParam(value = "acquiredBy", required = false) Long acquiredBy,
                                  @RequestParam(value = "userNote", required = false) String userNote) {
        List<Task> result;

        if (acquiredBy != null && userNote != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Concurrent usage of acquiredId and userNote query params");
        } else if (acquiredBy != null) {
            result = taskRepository.findByAcquiredBy(acquiredBy);
        } else if (userNote != null) {
            result = taskRepository.findByLikeUserNote(userNote);
        } else {
            result = taskRepository.findAll();
        }

        return result.stream()
                .map(TaskDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public TaskDto getTaskById(@PathVariable long id) {
        Task task = taskRepository.findById(id);
        if (task == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found: id=" + id);
        }

        return new TaskDto(task);
    }

    @PostMapping
    public ResponseEntity<Object> createTask(@RequestBody TaskCreateDto taskDto) {
        validateCreatePayload(taskDto);

        Task task = taskDto.buildTaskInstance();
        taskRepository.insert(task);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateTask(@RequestBody TaskUpdateDto taskDto, @PathVariable long id) {
        validateUpdatePayload(taskDto);

        Task persistedTask = taskRepository.findById(id);
        if (persistedTask == null) {
            return ResponseEntity.notFound().build();
        }

        Task task = taskDto.buildTaskInstance(persistedTask);

        taskRepository.update(task);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public void deleteTask(@PathVariable long id) {
        taskRepository.deleteById(id);
    }

    private void validateCreatePayload(TaskCreateDto taskDto) {
        // TODO: implement e.g. checks if users identified by acquiredBy and/or createdBy exist
    }

    private void validateUpdatePayload(TaskUpdateDto taskDto) {
        // TODO: see above
    }
}
