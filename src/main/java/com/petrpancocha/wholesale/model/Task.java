package com.petrpancocha.wholesale.model;

public class Task {
    private Long id;
    private String userNote;
    private String taskData;
    private Long acquiredBy;
    private Long createdBy;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
