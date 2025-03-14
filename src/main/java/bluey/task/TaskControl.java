package bluey.task;

import java.util.ArrayList;
import bluey.storage.Storage;
import bluey.exception.BlueyException;
import bluey.ui.Parser;
import bluey.ui.Printer;

/**
 * Contains the functions required to perform operations on or using the task list
 * as specified by user commands
 */

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

    /**
     * Prints the list of all current tasks in the format below (curved brackets are placeholders).
     * (tasknumber). [task type] [X if marked done] (task description)
     */
    public void printList() {
        try {
            if (tasks.isEmpty()) {
                BlueyException.EmptyListException();
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

    /**
     * Attempts to mark or unmark the specified task in the user's response.
     * Does nothing and returns a message if the task's status (marked/unmarked) remains unchanged,
     * for example if user tries to mark an already-marked task.
     * @param userResponse User's response
     * @param status Whether user wants to Mark or Unmark the task.
     */
    public void toggleTaskStatus(String userResponse, String status) {
        try {
            int taskIndex = Parser.parseTaskNumber(userResponse);
            if (tasks.isEmpty()) {
                BlueyException.EmptyListException();
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
                BlueyException.InvalidCommandException("INVALID_TOGGLE_STATUS");
                break;
            }
            storage.save(tasks);
        } catch (BlueyException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Sorry, please provide a valid task number to mark or unmark!");
        }
    }

    /**
     * Helper function for toggleTaskStatus, to mark a task as done (if not already).
     * @param currentTask Task that user wishes to mark
     * @param taskIndex Task number that user wishes to mark
     */
    public void markTask(Task currentTask, int taskIndex) {
        if (!currentTask.isDone) {
            System.out.println("Ok! Task number " + (taskIndex + 1) + " marked as done!");
            currentTask.isDone = true;
            System.out.println("  " + currentTask);
        } else {
            System.out.println("Task already marked as done!");
        }
    }

    /**
     * Helper function for toggleTaskStatus, to unmark a task as done (if not already).
     * @param currentTask Task that user wishes to unmark
     * @param taskIndex Task number that user wishes to unmark
     */
    public void unmarkTask(Task currentTask, int taskIndex) {
        if (currentTask.isDone) {
            System.out.println("Ok! Task number " + (taskIndex + 1) + " marked as not done yet!");
            currentTask.isDone = false;
            System.out.println("  " + currentTask);
        } else {
            System.out.println("Task already marked as not done yet!");
        }
    }

    /**
     * Adds a new task to the list of tasks, as specified in the user's response.
     * @param userResponse User's response
     * @param taskType Whether the task's type is Deadline, Event, or ToDo, as specified by the user
     */
    public void addTask(String userResponse, String taskType) {
        try {
            String[] words = userResponse.split("\\s+", 2);
            if (words.length < 2) {
                if (taskType.equals("deadline") || taskType.equals("todo") || taskType.equals("event")) {
                    BlueyException.MissingNumberException();
                } else {
                    BlueyException.InvalidCommandException("DEFAULT");
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
            default -> BlueyException.InvalidCommandException("DEFAULT");
            }
            System.out.println("You now have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list!");
            storage.save(tasks);
        } catch (BlueyException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes an existing task from the list of tasks, as specified in the user's response.
     * @param userResponse User's response, in the form "delete (task number)"
     * @throws IndexOutOfBoundsException If the user-specified task number is not positive,
     * or larger than the number of existing tasks
     */
    public void deleteTask(String userResponse) throws IndexOutOfBoundsException {
        try {
            if (tasks.isEmpty()) {
                BlueyException.EmptyListException();
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

    /**
     * Finds and lists out all tasks in the list containing the word specified by the user.
     * @param userResponse User's response
     */
    public void find(String userResponse) {
        try {
            if (tasks.isEmpty()) {
                BlueyException.EmptyListException();
            }

            String[] words = userResponse.split("\\s+", 2);
            if (words.length < 2) {
                BlueyException.MissingNumberException();
            }

            String searchTerm = words[1].trim();
            if (searchTerm.contains(" ")) {
                BlueyException.InvalidCommandException("MULTI_WORD_SEARCH_STRING");
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
