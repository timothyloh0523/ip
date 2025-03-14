package bluey.command;

import bluey.task.TaskControl;
import bluey.ui.Parser;

public class CommandHandler {
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
