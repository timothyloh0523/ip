package bluey.task;

import java.util.ArrayList;
import bluey.storage.Storage;
import bluey.exception.BlueyException;

public class TaskControl {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    public TaskControl(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>();

        tasks.addAll(storage.load());
    }

    public void printList() {
        try {
            if (tasks.isEmpty()) {
                BlueyException.listException("EMPTY_LIST");
            } else {
                System.out.println("Here is your list!");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());
                }
            }
        } catch (BlueyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void toggleTaskStatus(int taskIndex, String status) throws IndexOutOfBoundsException {
        try {
            if (tasks.isEmpty()) {
                BlueyException.listException("EMPTY_LIST");
            }
            taskIndex = taskIndex - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            Task currentTask = tasks.get(taskIndex);
            switch (status) {
            case "mark":
                if (!currentTask.isDone) {
                    System.out.println("Ok! Task number " + (taskIndex + 1) + " marked as done!");
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
                    System.out.println("  " + currentTask);
                } else {
                    System.out.println("Task already marked as not done yet!");
                }
                break;
            default:
                System.out.println("Error! Unrecognised task status: " + status);
                break;
            }
            storage.save(tasks);
        } catch (BlueyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addTask(String userResponse) {
        try {
            String[] words = userResponse.split("\\s+", 2);
            String taskType = words[0].toLowerCase();
            if (words.length < 2) {
                if (taskType.equals("deadline") || taskType.equals("todo") || taskType.equals("event")) {
                    BlueyException.invalidTaskNumException("MISSING_NUMBER");
                } else {
                    BlueyException.invalidCommandException("DEFAULT");
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
            default -> BlueyException.invalidCommandException("DEFAULT");
            }
            System.out.println("You now have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list!");
            storage.save(tasks);
        } catch (BlueyException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(String userResponse) throws IndexOutOfBoundsException {
        try {
            if (tasks.isEmpty()) {
                BlueyException.listException("EMPTY_LIST");
            }
            String[] words = userResponse.split("\\s+", 2);
            int taskIndex = Integer.parseInt(words[1]);
            taskIndex = taskIndex - 1;
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            System.out.println("Okay! I've deleted this task from the list!");
            System.out.println("  " + tasks.get(taskIndex));
            tasks.remove(taskIndex);
            System.out.println("You now have " + tasks.size() + " tasks in the list!");
            storage.save(tasks);
        } catch (BlueyException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Sorry, please provide a valid task number to delete!");
        }
    }

    public void find(String userResponse) {
        try {
            if (tasks.isEmpty()) {
                BlueyException.listException("EMPTY_LIST");
            }

            String[] words = userResponse.split("\\s+", 2);
            if (words.length < 2) {
                BlueyException.invalidTaskNumException("MISSING_NUMBER");
            }

            String searchTerm = words[1].trim();
            if (searchTerm.contains(" ")) {
                BlueyException.invalidCommandException("MULTI_WORD_SEARCH_STRING");
            }

            boolean foundWord = false;
            System.out.println("Okay, here is your list of matching tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).description.contains(searchTerm)) {
                    foundWord = true;
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());
                }
            }
            if (!foundWord) {
                System.out.println("(no matching task found!)");
            }
        } catch (BlueyException e) {
            System.out.println(e.getMessage());
        }
    }
}
