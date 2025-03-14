package bluey.ui;

import bluey.task.Deadline;
import bluey.task.Event;

/*
 * The Parser class contains several functions to help break down
 * the user's response into pieces of information that the program understands.
 */

public class Parser {
    public static String parseFirstWord(String string) {
        String[] words = string.split("\\s+",2);
        return words[0];
    }
    public static int parseTaskNumber(String string) throws NumberFormatException, IndexOutOfBoundsException {
        String[] words = string.split("\\s+",2);
        return (Integer.parseInt(words[1]) - 1);
    }

    public static Deadline parseDeadline(String taskDetails) {
        String[] taskPartition = taskDetails.split("/by", 2);
        String taskDeadline = taskPartition[1].trim();
        String taskDescription = taskPartition[0].trim();
        return new Deadline(taskDescription, taskDeadline);
    }

    public static Event parseEvent(String taskDetails) {
        String[] taskPartition = taskDetails.split("/from", 2);
        String[] taskTimings = taskPartition[1].trim().split("/to", 2);
        String taskFrom = taskTimings[0].trim();
        String taskTo = taskTimings[1].trim();
        String taskDescription = taskPartition[0].trim();
        return new Event(taskDescription, taskFrom, taskTo);
    }
}
