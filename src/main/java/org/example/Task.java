package org.example;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Task {
    private String id;
    private String description;
    private LocalDate deadline;
    private LocalDate created;
    private boolean completed;
    private Set<String> tags;

    private TaskStatus status;

    private LocalDateTime modified;

    public Task(String id, String description, LocalDate deadline, LocalDate created, Set<String> tags) {
        this.id = id;
        this.description = description;
        this.deadline = deadline;
        this.created = created;
        this.completed = false;
        this.tags = tags;
        this.modified = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDate getCreated() {
        return created;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public LocalDateTime getModified() {
        return modified;
    }


    public String getStatus() {
        if (isCompleted()) {
            return "COMPLETED";
        } else if (getDeadline() == null) {
            return "INCOMPLETE";
        } else if (getDeadline().isBefore(LocalDate.now())) {
            return "OVERDUE";
        } else {
            return "IN PROGRESS";
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Task: ").append(description).append("\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Created: ").append(created).append("\n");
        sb.append("Deadline: ").append(deadline).append("\n");
        sb.append("Tags: ").append(tags).append("\n");
        sb.append("Completed: ").append(completed).append("\n");
        return sb.toString();
    }

    public void setModified(LocalDateTime modifiedTime) {
        this.modified = modifiedTime;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}
