package com.petrpancocha.wholesale;

import com.petrpancocha.wholesale.controller.TaskController;
import com.petrpancocha.wholesale.model.Task;
import com.petrpancocha.wholesale.repository.TaskMyBatisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskMyBatisRepository taskRepository;

    @Test
    public void testGetTaskById() throws Exception {
        long taskId = 1l;
        long createdBy = 1l;
        long acquiredBy = 1l;
        String userNote = "User note";
        String taskData = "Task data";

        Task mockTask = new Task();
        mockTask.setId(taskId);
        mockTask.setCreatedBy(createdBy);
        mockTask.setAcquiredBy(acquiredBy);
        mockTask.setUserNote(userNote);
        mockTask.setTaskData(taskData);

        when(taskRepository.findById(taskId)).thenReturn(mockTask);

        this.mockMvc
                .perform(get(String.format("/tasks/%d", taskId)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(taskId))
                .andExpect(jsonPath("$.createdBy").value(createdBy))
                .andExpect(jsonPath("$.acquiredBy").value(acquiredBy))
                .andExpect(jsonPath("$.userNote").value(userNote))
                .andExpect(jsonPath("$.taskData").value(taskData));
    }

    @Test
    public void testGetTaskByUnknownId() throws Exception {
        long unknownTaskId = 1l;

        when(taskRepository.findById(unknownTaskId)).thenReturn(null);

        this.mockMvc
                .perform(get(String.format("/tasks/%d", unknownTaskId)))
                .andDo(print())
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

    // TODO: add more tests
}
