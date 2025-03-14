package bluey.exception;

/**
 * The BlueyException class holds all custom-created exceptions specific to this project.
 * The exceptions thrown include various invalidCommandExceptions, missingNumber, emptyList and fileAlreadyExists exceptions.
 */

public class BlueyException extends Exception {

    private static final String EMPTY_LIST_MESSAGE = "I have not been given any tasks! Start by creating an event, todo or deadline :)";
    private static final String INVALID_COMMAND_MESSAGE = "Sorry! I do not understand the command you entered! Please try again :)";
    private static final String INVALID_TASK_TYPE_MESSAGE = "Oops! Please provide a valid task type - todo, event or deadline!";
    private static final String MISSING_TASK_NUMBER_MESSAGE = "Sorry, please try again by adding a space and a number after your command :)";
    private static final String FILE_ALREADY_EXISTS_MESSAGE = "Oh no, I couldn't create a new file, it already exists!";
    private static final String MULTI_WORD_SEARCH_MESSAGE = "Sorry! I can only search one word at a time, please try again!";
    private static final String INVALID_TOGGLE_STATUS_MESSAGE = "Error! Unrecognised task status!";

    public BlueyException(String message) {
        super(message);
    }

    public static void InvalidCommandException(String errorType) throws BlueyException {
        switch (errorType) {
        case "INVALID_TASK_TYPE":
            throw new BlueyException(INVALID_TASK_TYPE_MESSAGE);
        case "MULTI_WORD_SEARCH_STRING":
            throw new BlueyException(MULTI_WORD_SEARCH_MESSAGE);
        case "INVALID_TOGGLE_STATUS":
            throw new BlueyException(INVALID_TOGGLE_STATUS_MESSAGE);
        default:
            throw new BlueyException(INVALID_COMMAND_MESSAGE);
        }
    }

    public static void MissingNumberException() throws BlueyException {
        throw new BlueyException(MISSING_TASK_NUMBER_MESSAGE);
    }

    public static void EmptyListException() throws BlueyException {
        throw new BlueyException(EMPTY_LIST_MESSAGE);
    }

    public static void fileAlreadyExistsException() throws BlueyException {
        throw new BlueyException(FILE_ALREADY_EXISTS_MESSAGE);
    }
}
