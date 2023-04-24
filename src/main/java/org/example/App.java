package org.example;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class App {

    public static void main(String[] args) {
        // create task manager instance
        TaskManager taskManager = new TaskManager();

        // add tasks
        Set<String> tags1 = new HashSet<>(Arrays.asList("shopping", "food"));
        Task task1 = new Task("1", "Buy groceries", LocalDate.now().plusDays(1),LocalDate.now(), tags1);

        Set<String> tags2 = new HashSet<>(Arrays.asList("finance"));
        Task task2 = new Task("2", "Pay bills", LocalDate.now().plusDays(2), LocalDate.now(),tags2);

        Set<String> tags3 = new HashSet<>(Arrays.asList("home"));
        Task task3 = new Task("3", "Clean room", null, LocalDate.now(), tags3);

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        // complete a task
        taskManager.completeTask(task1.getId());

        // modify a task
        taskManager.modifyTask("2","Clean entire house", LocalDate.now().plusDays(3), new HashSet<>(Arrays.asList("home")));

        // remove a task
        taskManager.removeTask(task3.getId());

        //List all tasks
        LocalDate fromDate = LocalDate.now().minusDays(7);
        LocalDate toDate = LocalDate.now().plusDays(6);
        List<String> tags = Arrays.asList("finance");
        System.out.println("Tasks with tags and deadline range:");
        taskManager.listTasks(fromDate, toDate, tags, true).forEach(System.out::println);
        System.out.println();

        // get statistics
        System.out.println("Statistics:");
        System.out.println(taskManager.getStatistics(LocalDate.now().minusDays(7), LocalDate.now()));
        System.out.println();

        // get activity log
        System.out.println("Activity log:");
        System.out.println(taskManager.getActivityLog(LocalDate.now().minusDays(7), LocalDate.now()));
    }
}
