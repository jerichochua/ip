package duke.ui;

import duke.tasklist.TaskList;

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

    public void printTasks(TaskList tasks) {
        for (int i = 0; i < tasks.getUserTasksCount(); i++) {
            printToUser(String.format("\t%d. %s", i + 1, tasks.getTask(i)));
        }
    }
}
