package bluey.task;

import java.util.ArrayList;
import bluey.storage.Storage;
import bluey.exception.BlueyException;
import bluey.ui.Parser;
import bluey.ui.Printer;

public class TaskControl {
    private final ArrayList<Task> tasks;
    private final Storage storage;
    private final Printer printer;

    public TaskControl(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>();
        this.printer = new Printer();

        tasks.addAll(storage.load());
    }

    public void printList() {
        try {
            if (tasks.isEmpty()) {
                BlueyException.emptyListException();
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

    public void toggleTaskStatus(String userResponse, String status) {
        try {
            int taskIndex = Parser.parseTaskNumber(userResponse);
            if (tasks.isEmpty()) {
                BlueyException.emptyListException();
            }
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new IndexOutOfBoundsException();
            }
            Task currentTask = tasks.get(taskIndex);
            switch (status) {
            case "mark":
                markTask(currentTask, taskIndex);
                break;
            case "unmark":
                unmarkTask(currentTask, taskIndex);
                break;
            default:
                BlueyException.invalidCommandException("INVALID_TOGGLE_STATUS");
                break;
            }
            storage.save(tasks);
        } catch (BlueyException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Sorry, please provide a valid task number to mark or unmark!");
        }
    }

    public void markTask(Task currentTask, int taskIndex) {
        if (!currentTask.isDone) {
            System.out.println("Ok! Task number " + (taskIndex + 1) + " marked as done!");
            currentTask.isDone = true;
            System.out.println("  " + currentTask);
        } else {
            System.out.println("Task already marked as done!");
        }
    }

    public void unmarkTask(Task currentTask, int taskIndex) {
        if (currentTask.isDone) {
            System.out.println("Ok! Task number " + (taskIndex + 1) + " marked as not done yet!");
            currentTask.isDone = false;
            System.out.println("  " + currentTask);
        } else {
            System.out.println("Task already marked as not done yet!");
        }
    }

    public void addTask(String userResponse, String taskType) {
        try {
            String[] words = userResponse.split("\\s+", 2);
            if (words.length < 2) {
                if (taskType.equals("deadline") || taskType.equals("todo") || taskType.equals("event")) {
                    BlueyException.missingNumberException();
                } else {
                    BlueyException.invalidCommandException("DEFAULT");
                }
            }
            String taskDetails = words[1];
            switch (taskType) {
            case "deadline" -> {
                System.out.println("Okay! I'll add this " + taskType + " to the list!");
                Deadline newTask = Parser.parseDeadline(taskDetails);
                tasks.add(newTask);
                printer.printTask(newTask);
            }
            case "todo" -> {
                System.out.println("Okay! I'll add this " + taskType + " to the list!");
                ToDo newTask = new ToDo(taskDetails);
                tasks.add(newTask);
                printer.printTask(newTask);
            }
            case "event" -> {
                System.out.println("Okay! I'll add this " + taskType + " to the list!");
                Event newTask = Parser.parseEvent(taskDetails);
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
                BlueyException.emptyListException();
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
                BlueyException.emptyListException();
            }

            String[] words = userResponse.split("\\s+", 2);
            if (words.length < 2) {
                BlueyException.missingNumberException();
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
