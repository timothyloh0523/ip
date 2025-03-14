package bluey.task;

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
