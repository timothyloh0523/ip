import java.util.Scanner;

public class bluey {
    public static void main(String[] args) {
        System.out.println("Hello! I'm bluey!");
        System.out.println("What can I do for you?\n");
        String line;
        String[] list = new String[100];

        Scanner in = new Scanner(System.in);
        int itemCount = 0;
        while (true) {
            line = in.nextLine().trim();
            if (line.equalsIgnoreCase("bye")) {
                break;
            }
            if (line.equalsIgnoreCase("list")) {
                if (itemCount == 0) {
                    System.out.println("There are no items in the list");
                }
                for (int i = 0; i < itemCount; i++) {
                    System.out.println((i+1) + ". " + list[i]);
                }
            }
            else if (itemCount<100) {
                list[itemCount] = line.trim();
                System.out.println("added: " + line.trim());
                itemCount++;
            }
            else { // Over 100 items
                System.out.println("List full!");
            }
        }
        System.out.println("Bye, see you again soon!");
    }
}
