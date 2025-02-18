public class EmptyTaskDescException extends BlueyException {
    public EmptyTaskDescException(String taskType) {
        super("Sorry! " + taskType + " cannot be left empty! Please try again :)");
    }
}
