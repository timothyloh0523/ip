package bluey.exception;

public class BlueyException extends Exception {

    private static final String EMPTY_LIST_MESSAGE = "I have not been given any tasks! Start by creating an event, todo or deadline :)";
    private static final String INVALID_COMMAND_MESSAGE = "Sorry! I do not understand the command you entered! Please try again :)";
    private static final String MISSING_TASK_NUMBER_MESSAGE = "Sorry, please try again by adding a space and a number after your command :)";

    public BlueyException(String message) {
        super(message);
    }

    public static void invalidCommandException() throws BlueyException {
        throw new BlueyException(INVALID_COMMAND_MESSAGE);
    }

    public static void invalidTaskNumException(String errorType) throws BlueyException {
        switch (errorType) {
        case "MISSING_NUMBER":
            throw new BlueyException(MISSING_TASK_NUMBER_MESSAGE);
        }
    }

    public static void listException(String errorType) throws BlueyException {
        switch (errorType) {
        case "EMPTY_LIST":
            throw new BlueyException(EMPTY_LIST_MESSAGE);
        }
    }
}
