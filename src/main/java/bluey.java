import java.util.Scanner;

public class bluey {
    public static void main(String[] args) {
        System.out.println("Hello! I'm bluey!");
        System.out.println("What can I do for you?\n");

        TaskControl taskControl = new TaskControl();
        String userResponse;
        Scanner in = new Scanner(System.in);

        while (true) {
            userResponse = in.nextLine().trim();
            String[] words = userResponse.split("\\s+");
            switch (words[0]) {
            case "bye":
            case "exit":
                System.out.println("Bye, see you again soon!");
                return;
            case "list":
                taskControl.printList();
                break;
            case "mark":
            case "unmark": // exception handling to be added and reformatted in the future.
                if (words.length > 2) {
                    System.out.println("Sorry, I don't understand! Please try again with the format \"mark x\" " +
                            "or \"unmark x\" " + "to mark/unmark task number x!");
                } else if (words.length == 1) {
                    System.out.println("Sorry, please try again! Do specify a number after \"mark\" or \"unmark\" :)");
                } else { // to be added: check if words[1] is of type Integer (exception otherwise) and small enough.
                    taskControl.toggleTaskStatus(Integer.parseInt(words[1]), words[0]);
                }
                break;
            default:
                if (taskControl.getTaskCount() < 100) {
                    taskControl.addTask(userResponse);
                } else { // 100 items
                    System.out.println("List full! Sorry :(");
                }
                break;
            }
        }

    }
}
