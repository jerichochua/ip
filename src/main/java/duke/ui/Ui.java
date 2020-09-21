package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the user's input and the printing of messages to the user.
 */
public class Ui {
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";

    private final Scanner in = new Scanner(System.in);

    /**
     * Gets the command from the user.
     *
     * @return command entered by the user
     */
    public String getUserInput() {
        return in.nextLine();
    }

    /**
     * Prints a welcome message when the application starts.
     */
    public void printWelcomeMessage() {
        printToUser(MESSAGE_WELCOME);
    }

    /**
     * Prints a message when the application is terminated.
     */
    public void printExitMessage() {
        printToUser(MESSAGE_EXIT);
    }

    /**
     * Prints a message to the user.
     *
     * @param message message to be printed to the user
     */
    public void printToUser(String message) {
        System.out.println(message);
    }

    /**
     * Prints all the tasks in the task list to the user.
     *
     * @param tasks the task list with the contents to be printed to the user
     */
    public void printAllTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getUserTasksCount(); i++) {
            printToUser(String.format("\t%d. %s", i + 1, tasks.getTask(i)));
        }
    }

    /**
     * Prints a message to indicate that a task was added.
     */
    public void printTaskAdded(Task task) {
        printToUser("\tAdded: " + task);
    }

    /**
     * Prints the number of tasks left in the task list.
     *
     * @param tasks the task list with the user's tasks
     */
    public void printRemainingTasks(TaskList tasks) {
        int taskCount = tasks.getUserTasksCount();
        String addS = (taskCount > 1) ? "s" : "";
        System.out.println("\tYou now have " + taskCount + " task" + addS + " in your list!");
    }

    /**
     * Prints a message to indicate that a task was marked as done.
     */
    public void printTaskDone(Task task) {
        printToUser("\tI have marked the following task as done:");
        printToUser(String.format("\t\t%s", task));
    }

    /**
     * Prints a message to indicate that a task was deleted.
     */
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

    public void printDateTimeError() {
        printToUser("\tError: Date/time cannot be recognised!");
    }
}
