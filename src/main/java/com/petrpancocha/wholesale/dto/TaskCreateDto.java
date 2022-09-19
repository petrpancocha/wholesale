package com.petrpancocha.wholesale.dto;

import com.petrpancocha.wholesale.model.Task;

public class TaskCreateDto {
    private String userNote;
    private String taskData;
    private Long acquiredBy;
    private Long createdBy;

    public Task buildTaskInstance() {
        Task task = new Task();

        task.setUserNote(this.userNote);
        task.setTaskData(this.taskData);
        task.setAcquiredBy(this.acquiredBy);
        task.setCreatedBy(this.createdBy);

        return task;
    }

    public String getUserNote() {
        return this.userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    public String getTaskData() {
        return this.taskData;
    }

    public void setTaskData(String taskData) {
        this.taskData = taskData;
    }

    public Long getAcquiredBy() {
        return this.acquiredBy;
    }

    public void setAcquiredBy(Long acquiredBy) {
        this.acquiredBy = acquiredBy;
    }

    public Long getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }
}
