package com.petrpancocha.wholesale.dto;

import com.petrpancocha.wholesale.model.Task;

public class TaskDto {
    private Long id;
    private String userNote;
    private String taskData;
    private Long acquiredBy;
    private Long createdBy;

    public TaskDto(Task task) {
        this.id = task.getId();
        this.userNote = task.getUserNote();
        this.taskData = task.getTaskData();
        this.acquiredBy = task.getAcquiredBy();
        this.createdBy = task.getCreatedBy();
    }

    public Long getId() {
        return this.id;
    }

    public String getUserNote() {
        return this.userNote;
    }

    public String getTaskData() {
        return this.taskData;
    }

    public Long getAcquiredBy() {
        return this.acquiredBy;
    }

    public Long getCreatedBy() {
        return this.createdBy;
    }
}
