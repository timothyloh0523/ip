package bluey.exception;

public class EmptyListException extends BlueyException {
    public EmptyListException() {
        super("I have not been given any tasks! Start by creating an event, todo or deadline :)");
    }
}
