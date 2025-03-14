package bluey.task;

/*
 * The Task class represents a task that the user wishes to record.
 * @param description Name of task
 * @param isDone Boolean variable stating whether the task has been completed.
 */

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return description;
    }
}