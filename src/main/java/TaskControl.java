public class TaskControl {
    private final Task[] tasks;
    private int taskCount;

    public TaskControl() {
        int MAX_TASKS = 100;
        this.tasks = new Task[MAX_TASKS];
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
                System.out.println((i + 1) + ". " + tasks[i].toString());
            }
        }
    }

    public void toggleTaskStatus(int taskIndex, String status) {
        taskIndex = taskIndex - 1;
        switch (status) {
        case "mark":
            if (!tasks[taskIndex].isDone) {
                System.out.println("Task number " + (taskIndex + 1) + " marked as done!");
                tasks[taskIndex].isDone = true;
                System.out.println("  " + tasks[taskIndex].toString());
            } else {
                System.out.println("Task already marked as done!");
            }
            break;
        case "unmark":
            if (tasks[taskIndex].isDone) {
                System.out.println("Ok! Task number " + (taskIndex + 1) + " marked as not done yet!");
                tasks[taskIndex].isDone = false;
                System.out.println("[" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].description);
            } else {
                System.out.println("Task already marked as not done yet!");
            }
            break;
        default:
            System.out.println("Error! Unrecognised task status: " + status);
            break;
        }
    }

    public void addTask(String userResponse) throws InvalidCommandException {
        // assumed tasks[] has min 1 free slot. exception handling to be added in future update
        if (userResponse.split(" ").length < 2) {
            throw new InvalidCommandException(userResponse);
        }
        String taskDetails = userResponse.split(" ", 2)[1];
        String taskType = userResponse.split(" ", 2)[0].toLowerCase();

        switch (taskType) {
        case "deadline" -> {
            System.out.println("Okay! I'll add this " + taskType + " to the list!");
            String[] taskPartition = taskDetails.split("/by", 2);
            String taskDeadline = taskPartition[1].trim();
            String taskDescription = taskPartition[0].trim();
            tasks[taskCount] = new Deadline(taskDescription, taskDeadline);
            System.out.println("  " + tasks[taskCount].toString());
            taskCount++;
            System.out.println("You now have " + taskCount + " tasks in the list!");
        }
        case "todo" -> {
            System.out.println("Okay! I'll add this " + taskType + " to the list!");
            tasks[taskCount] = new ToDo(taskDetails);
            System.out.println("  " + tasks[taskCount].toString());
            taskCount++;
            System.out.println("You now have " + taskCount + " tasks in the list!");
        }
        case "event" -> {
            System.out.println("Okay! I'll add this " + taskType + " to the list!");
            String[] taskPartition = taskDetails.split("/from", 2);
            String[] taskTimings = taskPartition[1].trim().split("/to", 2);
            String taskFrom = taskTimings[0].trim();
            String taskTo = taskTimings[1].trim();
            String taskDescription = taskPartition[0].trim();
            tasks[taskCount] = new Event(taskDescription, taskFrom, taskTo);
            System.out.println("  " + tasks[taskCount].toString());
            taskCount++;
            System.out.println("You now have " + taskCount + " tasks in the list!");
        }
        default -> throw new InvalidCommandException(userResponse);
        }
    }
}
