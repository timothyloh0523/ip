package bluey.task;

import java.util.ArrayList;
import bluey.exception.InvalidCommandException;
import bluey.exception.EmptyTaskDescException;

public class TaskControl {
    private final ArrayList<Task> tasks;
    private int taskCount;

    public TaskControl() {
        this.tasks = new ArrayList<>();
        taskCount = 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void printList() {
        if (taskCount == 0) {
            System.out.println("I have not been given any tasks! Start by creating an event, todo or deadline :)");
        } else {
            System.out.println("Here is your list!");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    public void toggleTaskStatus(int taskIndex, String status) throws IndexOutOfBoundsException {
        taskIndex = taskIndex - 1;
        if (taskIndex < 0 || taskIndex >= taskCount) {
            throw new IndexOutOfBoundsException();
        }
        Task currentTask = tasks.get(taskIndex);
        switch (status) {
        case "mark":
            if (!currentTask.isDone) {
                System.out.println("Task number " + (taskIndex + 1) + " marked as done!");
                currentTask.isDone = true;
                System.out.println("  " + currentTask);
            } else {
                System.out.println("Task already marked as done!");
            }
            break;
        case "unmark":
            if (currentTask.isDone) {
                System.out.println("Ok! Task number " + (taskIndex + 1) + " marked as not done yet!");
                currentTask.isDone = false;
                System.out.println("[" + currentTask.getStatusIcon() + "] " + currentTask.description);
            } else {
                System.out.println("Task already marked as not done yet!");
            }
            break;
        default:
            System.out.println("Error! Unrecognised task status: " + status);
            break;
        }
    }

    public void addTask(String userResponse) throws InvalidCommandException, EmptyTaskDescException {
        String[] words = userResponse.split("\\s+", 2);
        String taskType = words[0].toLowerCase();
        if (words.length < 2) {
            if (taskType.equals("deadline") || taskType.equals("todo") || taskType.equals("event")) {
                throw new EmptyTaskDescException(taskType);
            } else {
                throw new InvalidCommandException(taskType);
            }
        }
        String taskDetails = words[1];
        switch (taskType) {
        case "deadline" -> {
            System.out.println("Okay! I'll add this " + taskType + " to the list!");
            String[] taskPartition = taskDetails.split("/by", 2);
            String taskDeadline = taskPartition[1].trim();
            String taskDescription = taskPartition[0].trim();
            Deadline newTask = new Deadline(taskDescription, taskDeadline);
            tasks.add(newTask);
            System.out.println("  " + newTask);
        }
        case "todo" -> {
            System.out.println("Okay! I'll add this " + taskType + " to the list!");
            ToDo newTask = new ToDo(taskDetails);
            tasks.add(newTask);
            System.out.println("  " + newTask);
        }
        case "event" -> {
            System.out.println("Okay! I'll add this " + taskType + " to the list!");
            String[] taskPartition = taskDetails.split("/from", 2);
            String[] taskTimings = taskPartition[1].trim().split("/to", 2);
            String taskFrom = taskTimings[0].trim();
            String taskTo = taskTimings[1].trim();
            String taskDescription = taskPartition[0].trim();
            Event newTask = new Event(taskDescription, taskFrom, taskTo);
            tasks.add(newTask);
            System.out.println("  " + newTask);
        }
        default -> throw new InvalidCommandException(userResponse);
        }
        taskCount++;
        if (taskCount == 1) {
            System.out.println("You now have " + taskCount + " task in the list!");
        } else {
            System.out.println("You now have " + taskCount + " tasks in the list!");
        }
    }

    public void deleteTask(int taskIndex) throws IndexOutOfBoundsException {
        taskIndex = taskIndex - 1;
        if (taskIndex < 0 || taskIndex >= taskCount) {
            throw new IndexOutOfBoundsException();
        }
        taskCount--;
        System.out.println("Okay! I've deleted this task from the list!");
        System.out.println("  " + tasks.get(taskIndex));
        System.out.println("You now have " + taskCount + " tasks in the list!");
        tasks.remove(taskIndex);
    }
}
