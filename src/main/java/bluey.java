import java.util.Scanner;

public class bluey {
    public static void main(String[] args) {
        System.out.println("Hello! I'm bluey!");
        System.out.println("What can I do for you?\n");
        String line;
        Task[] list = new Task[100];

        Scanner in = new Scanner(System.in);
        int itemCount = 0;
        while (true) {
            line = in.nextLine().trim();
//            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                break;
            }
            if (line.equalsIgnoreCase("list")) {
                if (itemCount == 0) {
                    System.out.println("There are no items in the list");
                }
                else {
                    System.out.println("Here is your list!");
                    for (int i = 0; i < itemCount; i++) {
                        System.out.println((i + 1) + ".[" + list[i].getStatusIcon() + "] " + list[i].description);
                    }
                }
            }
            else if (line.trim().substring(0,4).equalsIgnoreCase("mark")) {
//            else if (line.substring(0,4).equalsIgnoreCase("mark")) {
                System.out.println("Following task marked as done!");
                int taskIndex = Integer.parseInt(line.trim().substring(5))-1;
//                int taskIndex = Integer.parseInt(line.substring(5))-1;
                list[taskIndex].isDone = true;
                System.out.println("[" + list[taskIndex].getStatusIcon() + "] " + list[taskIndex].description);// add [x] return book code
            }
            else if (itemCount<100) {
                list[itemCount] = new Task(line.trim());
//                list[itemCount] = new Task(line);
                System.out.println("added: " + line.trim());
//                System.out.println("added: " + line);
                itemCount++;
            }
            else { // Over 100 items
                System.out.println("List full!");
            }
        }
        System.out.println("Bye, see you again soon!");
    }
}
