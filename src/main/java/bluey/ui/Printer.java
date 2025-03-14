package bluey.ui;

import bluey.task.Task;

public class Printer {

    public void printHello() {
        System.out.println("Hello! I'm bluey!");
        System.out.println("What can I do for you?");
    }

    public void printGoodbye() {
        System.out.println("Goodbye! See you soon :)");
    }

    public void printTask(Task task) {
        System.out.println("  " + task);
    }

}
