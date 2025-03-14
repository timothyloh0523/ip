package bluey.task;

/*
 * The Event class represents a task type, with an additional start and end time.
 * @param description Name of task.
 * @param from Time when event starts.
 * @param to Time when event ends.
 */

public class Event extends Task {

    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from.trim();
        this.to = to.trim();
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
