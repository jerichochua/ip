package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private final Scanner in = new Scanner(System.in);

    public String getUserInput() {
        return in.nextLine();
    }

    public void printWelcomeMessage() {
        printToUser(MESSAGE_WELCOME);
    }

    public void printExitMessage() {
        printToUser(MESSAGE_EXIT);
    }

    public void printToUser(String message) {
        System.out.println(message);
    }

    public void printAllTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getUserTasksCount(); i++) {
            printToUser(String.format("\t%d. %s", i + 1, tasks.getTask(i)));
        }
    }

    public void printTaskAdded(Task task) {
        printToUser("\tAdded: " + task);
    }

    public void printRemainingTasks(TaskList tasks) {
        int taskCount = tasks.getUserTasksCount();
        String addS = (taskCount > 1) ? "s" : "";
        System.out.println("\tYou now have " + taskCount + " task" + addS + " in your list!");
    }

    public void printTaskDone(Task task) {
        printToUser("\tI have marked the following task as done:");
        printToUser(String.format("\t\t%s", task));
    }

    public void printTaskDeleted(Task task) {
        printToUser("\tOk, I have removed this task:");
        printToUser(String.format("\t\t%s", task));
    }

    public void printFilteredTasks(ArrayList<Task> filteredTasks) {
        printToUser("\tHere are the matching tasks in your list:");
        for (int i = 0; i < filteredTasks.size(); i++) {
            printToUser(String.format("\t\t%d. %s", i + 1, filteredTasks.get(i)));
        }
    }

    public void printFileError() {
        printToUser("\tError: The file cannot be written to!");
    }

    public void printFileLoadError() {
        printToUser("\tError: The file cannot be opened or created!");
    }
}
