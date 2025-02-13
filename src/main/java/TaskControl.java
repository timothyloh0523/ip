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
            System.out.println("There are no items in the list");
        } else {
            System.out.println("Here is your list!");
            for (int i = 0; i < taskCount; i++) {
                System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
            }
        }
    }

    public void markTaskAsDone(int taskIndex) {
        taskIndex = taskIndex - 1;
        if (!tasks[taskIndex].isDone) {
            System.out.println("Task number " + (taskIndex + 1) + " marked as done!");
            tasks[taskIndex].isDone = true;
            System.out.println("[" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].description);// add [x] return book code
        } else {
            System.out.println("Task already marked as done!");
        }
    }

    public void addTask(String description) {
        // assumed tasks[] has min 1 free slot. exception handling to be added in future update
        tasks[taskCount] = new Task(description);
//                list[itemCount] = new Task(line);
        System.out.println("Task successfully added: \"" + description + "\"!");
//                System.out.println("added: " + line);
        taskCount++;
    }

}
