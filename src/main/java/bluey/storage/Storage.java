package bluey.storage;

import bluey.exception.BlueyException;
import bluey.task.Task;
import bluey.task.ToDo;
import bluey.task.Event;
import bluey.task.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file into an ArrayList.
     * If the file does not exist, it creates an empty list.
     */
    public ArrayList<Task> load() throws IOException, FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
                return tasks;
            } catch (IOException e) {
                System.out.println("Sorry! There was an error when creating the file. Please try again!");
            }
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String task = scanner.nextLine();
            String[] words = task.split("\\|", 5);
            String taskType = words[0].trim();
            boolean isDone = "X".equals(words[1].trim());
            String description = words[2].trim();
            switch (taskType) {
            case "T":
                tasks.add(new ToDo(description));
                tasks.get(tasks.size() - 1).setIsDone(isDone);
                break;
            case "D":
                tasks.add(new Deadline(description, words[3].trim()));
                tasks.get(tasks.size() - 1).setIsDone(isDone);
                break;
            case "E":
                tasks.add(new Event(description, words[3].trim(), words[4].trim()));
                tasks.get(tasks.size() - 1).setIsDone(isDone);
                break;
            }
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws IOException, BlueyException {
        try {
            FileWriter filewriter = new FileWriter(filePath);
            for (Task task : tasks) {
                if (task instanceof ToDo) {
                    filewriter.write("T | " + task.getStatusIcon() + " | " + task.getDescription() + "\n");
                } else if (task instanceof Deadline) {
                    filewriter.write("D | " + task.getStatusIcon() + " | " + task.getDescription() + " | " + ((Deadline) task).getTaskDeadline() + "\n");
                } else if (task instanceof Event) {
                    filewriter.write("E | " + task.getStatusIcon() + " | " + task.getDescription() + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo() + "\n");
                } else {
                    BlueyException.invalidCommandException("INVALID_TASK_TYPE");
                }
            }
            filewriter.close();
            System.out.println("Successfully updated stored data!");
        } catch (IOException e) {
            System.out.println("Sorry! There was an error when updating the stored data!");
        } catch (BlueyException e) {
            System.out.println(e.getMessage());
        }
    }
}
