package com.petrpancocha.wholesale.dto;

import com.petrpancocha.wholesale.model.Task;

public class TaskUpdateDto {
    private String userNote;
    private String taskData;
    private Long acquiredBy;

    // Note: id and createdBy are not updateable fields

    public Task buildTaskInstance(Task originalTask) {
        Task task = new Task();

        task.setUserNote(this.userNote);
        task.setTaskData(this.taskData);
        task.setAcquiredBy(this.acquiredBy);

        task.setId(originalTask.getId());
        task.setCreatedBy(originalTask.getCreatedBy());

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
}
