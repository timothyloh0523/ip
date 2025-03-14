package bluey.command;

import bluey.task.TaskControl;
import bluey.ui.Parser;

/**
 * Interprets the user's input and redirects the program to the appropriate taskControl functions required.
 */

public class CommandHandler {

    /**
     * Function to handle the input command.
     * @param userResponse User input string, taken exactly character for character
     * @param taskControl Task control class to receive the interpreted commands.
     */
    public static void handleCommand(String userResponse, TaskControl taskControl) {
        String command = Parser.parseFirstWord(userResponse);
        switch (command) {
        case "list":
            taskControl.printList();
            break;
        case "mark":
            // Fallthrough
        case "unmark": // exception handling to be added and reformatted in the future.
            taskControl.toggleTaskStatus(userResponse, command);
            break;
        case "delete":
            taskControl.deleteTask(userResponse);
            break;
        case "find":
            taskControl.find(userResponse);
            break;
        default:
            taskControl.addTask(userResponse, command);
            break;
        }
    }
}
