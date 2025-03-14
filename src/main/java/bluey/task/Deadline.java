package bluey.task;

/*
 * The Deadline class represents a task type, with an additional deadline.
 * @param description Name of task.
 * @param taskDeadline Due date of task.
 */

public class Deadline extends Task {

    protected String taskDeadline;

    public Deadline(String description, String taskDeadline) {
        super(description);
        this.taskDeadline = taskDeadline;
    }

    public String getTaskDeadline() {
        return taskDeadline;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + super.toString() + " (by: " + taskDeadline + ")";
    }
}
