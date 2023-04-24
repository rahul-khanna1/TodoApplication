package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


public class TaskManager {
    private List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }



    public Task getTask(String taskId) {
        return tasks.stream()
                .filter(t -> t.getId().equals(taskId))
                .findFirst()
                .orElse(null);
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public void modifyTask(String taskId, String newDescription, LocalDate newDeadline, Set<String> newTags) {
        Task task = getTask(taskId);
        if (task != null) {
            task.setDescription(newDescription);
            task.setDeadline(newDeadline);
            task.setTags(newTags);
            task.setModified(getCurrentTime());
        }
    }

    private LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }


    public void removeTask(String taskId) {
        tasks.removeIf(t -> t.getId().equals(taskId));
    }

    public void completeTask(String taskId) {
        Task task = getTask(taskId);
        if (task != null && !task.isCompleted()) {
            task.setCompleted(true);
        }
    }

    public List<Task> listTasks(LocalDate fromDate, LocalDate toDate, List<String> tags, boolean sortByDeadline) {
        return tasks.stream()
                .filter(t -> t.getDeadline() != null && t.getDeadline().isAfter(fromDate.minusDays(1))
                        && t.getDeadline().isBefore(toDate.plusDays(1))
                        && (tags.isEmpty() || t.getTags().containsAll(tags)))
                .sorted(sortByDeadline ? Comparator.comparing(Task::getDeadline) : Comparator.comparing(Task::getCreated))
                .collect(Collectors.toList());
    }

    public Map<String, Long> getStatistics(LocalDate fromDate, LocalDate toDate) {
        return tasks.stream()
                .filter(t -> t.getCreated().isAfter(fromDate.minusDays(1)) && t.getCreated().isBefore(toDate.plusDays(1)))
                .collect(Collectors.groupingBy(Task::getStatus, Collectors.counting()));
    }

    public List<String> getActivityLog(LocalDate fromDate, LocalDate toDate) {
        return tasks.stream()
                .filter(t -> t.getModified().isAfter(fromDate.minusDays(1).atStartOfDay()) && t.getModified().isBefore(toDate.plusDays(1).atStartOfDay()))
                .sorted(Comparator.comparing(Task::getModified))
                .map(t -> t.getModified() + " - " + t.getStatus() + " - " + t.getDescription())
                .collect(Collectors.toList());
    }


}
