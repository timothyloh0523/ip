public class InvalidCommandException extends BlueyException {
    public InvalidCommandException(String command) {
        super("Sorry! I do not understand the command \"" + command + "\"! Please try again :)");
    }
}
