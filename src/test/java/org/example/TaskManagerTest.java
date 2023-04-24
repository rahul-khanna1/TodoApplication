package org.example;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;
import java.util.*;

public class TaskManagerTest {
    private TaskManager taskManager;

    @Test
    public void testAddTask() {
        taskManager = new TaskManager();
        Task task = new Task("1", "Test task", LocalDate.now().plusDays(1), LocalDate.now(), Collections.emptySet());
        taskManager.addTask(task);

        Assertions.assertEquals(1, taskManager.getTasks().size());
        Assertions.assertEquals(task, taskManager.getTasks().get(0));
    }

    @Test
    public void testCompleteTask() {
        taskManager = new TaskManager();
        Task task = new Task("1", "Test task", LocalDate.now().plusDays(1), LocalDate.now(), Collections.emptySet());
        taskManager.addTask(task);
        taskManager.completeTask("1");

//        Assertions.assertEquals(TaskStatus.COMPLETED, task.getStatus());
    }

    @Test
    public void testRemoveTask() {
        taskManager = new TaskManager();
        Task task = new Task("1", "Test task", LocalDate.now().plusDays(1), LocalDate.now(), Collections.emptySet());
        taskManager.addTask(task);
        taskManager.removeTask("1");

        Assertions.assertEquals(0, taskManager.getTasks().size());
    }

    @Test
    public void testListTasks() {
        taskManager = new TaskManager();
        Set<String> tags = new HashSet<>(Arrays.asList("shopping", "food"));
        Task task1 = new Task("1", "Buy groceries", LocalDate.now().plusDays(1), LocalDate.now(), tags);
        Task task2 = new Task("2", "Pay bills", LocalDate.now().plusDays(2), LocalDate.now(), Collections.singleton("finance"));
        Task task3 = new Task("3", "Clean room", null, LocalDate.now(), Collections.singleton("home"));

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        LocalDate fromDate = LocalDate.now().minusDays(1);
        LocalDate toDate = LocalDate.now().plusDays(3);
        List<String> tagList = Collections.singletonList("shopping");
        List<Task> tasks = taskManager.listTasks(fromDate, toDate, tagList, true);

//        Assertions.assertEquals(1, tasks.size());
//        Assertions.assertEquals(task1, tasks.get(0));
    }

    @Test
    public void testGetStatistics() {
        taskManager = new TaskManager();
        Task task1 = new Task("1", "Test task 1", LocalDate.now().plusDays(1), LocalDate.now(), Collections.emptySet());
        Task task2 = new Task("2", "Test task 2", LocalDate.now().plusDays(2), LocalDate.now(), Collections.emptySet());
        Task task3 = new Task("3", "Test task 3", LocalDate.now().plusDays(3), LocalDate.now(), Collections.emptySet());

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        Map<String, Long> statistics = taskManager.getStatistics(LocalDate.now(), LocalDate.now().plusDays(6));

        Assertions.assertEquals(3, statistics.get("IN PROGRESS"));
    }
}