package bluey.ui;

import bluey.task.TaskControl;
import bluey.exception.InvalidCommandException;
import bluey.exception.EmptyTaskDescException;
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
                if (words.length == 1) {
                    System.out.println("Sorry, please try again! Do specify a number after \"mark\" or \"unmark\" :)");
                } else if (words[1].contains(" ")) {
                    System.out.println("Sorry, I don't understand! Please try again with the format \"mark x\" " +
                            "or \"unmark x\" " + "to mark/unmark task number x!");
                } else { // to be added: check if words[1] is of type Integer (exception otherwise) and small enough.
                    taskControl.toggleTaskStatus(Integer.parseInt(words[1]), words[0]);
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Sorry, please provide a valid task number!");
            }
            break;
        case "delete":
            try {
                int taskIndex = Integer.parseInt(words[1]);
                taskControl.deleteTask(taskIndex);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                System.out.println("Sorry, please provide a valid task number!");
            }
            break;
        default:
            try {
                taskControl.addTask(userResponse);
            } catch (InvalidCommandException | EmptyTaskDescException e) {
                System.out.println(e.getMessage());
            }
            break;
        }
    }
}
