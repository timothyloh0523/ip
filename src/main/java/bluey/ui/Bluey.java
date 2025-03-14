package bluey.ui;

import bluey.task.TaskControl;
import bluey.storage.Storage;
import bluey.command.CommandHandler;
import java.util.Scanner;

public class Bluey {

    private final Printer printer;
    private final TaskControl taskControl;

    public Bluey(String filePath) {
        Storage storage = new Storage(filePath);
        this.taskControl = new TaskControl(storage);
        this.printer = new Printer();
    }

    public void run() {
        printer.printHello();
        Scanner in = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            String userResponse = in.nextLine().trim();
            if (userResponse.equals("exit") || userResponse.equals("bye")) {
                isExit = true;
            } else {
                CommandHandler.handleCommand(userResponse, taskControl);
            }
        }
        printer.printGoodbye();
    }

    public static void main(String[] args) {
        new Bluey("data.txt").run();
    }
}
