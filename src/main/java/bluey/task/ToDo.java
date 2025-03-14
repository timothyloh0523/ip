package bluey.task;

/*
 * The ToDo class represents a task type.
 * @param description Name of task
 */

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + super.toString();
    }
}
