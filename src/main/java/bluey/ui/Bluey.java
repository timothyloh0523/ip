package bluey.ui;

import bluey.task.TaskControl;
import java.util.Scanner;

public class Bluey {
    public static void main(String[] args) {
        System.out.println("Hello! I'm bluey!");
        System.out.println("What can I do for you?\n");

        TaskControl taskControl = new TaskControl();
        Scanner in = new Scanner(System.in);
        while (true) {
            String userResponse = in.nextLine().trim();
            if (userResponse.equals("exit") || userResponse.equals("bye")) {
                break;
            }
            handleResponse(userResponse, taskControl);
        }
        System.out.println("Goodbye! See you soon :)");
    }

    public static void handleResponse(String userResponse, TaskControl taskControl) {
        String[] words = userResponse.split("\\s+",2);
        switch (words[0]) {
        case "list":
            taskControl.printList();
            break;
        case "mark":
            // Fallthrough
        case "unmark": // exception handling to be added and reformatted in the future.
            try {
                int taskIndex = Integer.parseInt(words[1]);
                taskControl.toggleTaskStatus(taskIndex, words[0]);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Sorry, please provide a valid task number to delete!");
            }
            break;
        case "delete":
            taskControl.deleteTask(userResponse);
            break;
        default:
            taskControl.addTask(userResponse);
            break;
        }
    }
}
