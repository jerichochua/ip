package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final int MAX_SIZE = 100;
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private static ArrayList<Task> userTasks = new ArrayList<>(MAX_SIZE);
    private static int userTasksCount = 0;

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        printWelcomeMessage();
        userInput = in.nextLine();

        while (!userInput.equals(COMMAND_BYE)) {
            processUserInput(userInput);
            userInput = in.nextLine();
        }

        printExitMessage();
    }

    public static void processUserInput(String userInput) {
        int taskNumber;

        if (userInput.equals(COMMAND_LIST)) {
            listUserTasks(userTasksCount);
            return;
        }

        try {
            String userCommand = userInput.substring(0, userInput.indexOf(" "));
            String arguments = userInput.substring(userInput.indexOf(" ") + 1);
            switch (userCommand) {
            case COMMAND_TODO:
                addTodo(arguments);
                break;
            case COMMAND_DEADLINE:
                addDeadline(arguments);
                break;
            case COMMAND_EVENT:
                addEvent(arguments);
                break;
            case COMMAND_DONE:
                taskNumber = Integer.parseInt(arguments);
                markTaskAsDone(taskNumber);
                break;
            case COMMAND_DELETE:
                taskNumber = Integer.parseInt(arguments);
                deleteTask(taskNumber);
                break;
            default:
                throw new IllegalCommandException();
            }
        } catch (StringIndexOutOfBoundsException | IllegalCommandException e) {
            System.out.println("\tWrong command entered!");
        } catch (EmptyDescriptionException e) {
            System.out.println("\tDescription cannot be empty!");
        } catch (NullPointerException | NumberFormatException e) {
            System.out.println("\tInvalid task number entered!");
        }
    }

    public static void addTodo(String description) throws EmptyDescriptionException {
        if (description.length() == 0) {
            throw new EmptyDescriptionException();
        }
        userTasks.add(new Todo(description));
        addTaskSuccess();
    }

    public static void addDeadline(String arguments) {
        String[] argumentSplit = arguments.split(" /by ");
        try {
            userTasks.add(new Deadline(argumentSplit[0], argumentSplit[1]));
            addTaskSuccess();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tDescription or deadline cannot be empty!");
        }
    }

    public static void addEvent(String arguments) {
        String[] argumentSplit = arguments.split(" /at ");
        try {
            userTasks.add(new Event(argumentSplit[0], argumentSplit[1]));
            addTaskSuccess();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tDescription or event date/time cannot be empty!");
        }
    }

    public static void addTaskSuccess() {
        System.out.println("\tAdded: " + userTasks.get(userTasksCount));
        userTasksCount++;
        String addS = (userTasksCount > 1) ? "s" : "";
        System.out.println("\tYou now have " + userTasksCount + " task" + addS + " in your list.");
    }

    public static void listUserTasks(int taskCount) {
        System.out.println("\tHere are your tasks:");
        for (int i = 1; i <= taskCount; i++) {
            System.out.format("\t%d. %s\n", i, userTasks.get(i - 1));
        }
    }

    public static void markTaskAsDone(int taskNumber) {
        userTasks.get(taskNumber - 1).markAsDone();
        System.out.println("\tI have marked the following task as done:");
        System.out.format("\t\t%s\n", userTasks.get(taskNumber - 1));
    }

    public static void deleteTask(int taskNumber) {
        try {
            Task removedTask = userTasks.remove(taskNumber - 1);
            userTasksCount--;
            System.out.println("\tOk, I have removed this task:");
            System.out.format("\t\t%s\n", removedTask);
            String addS = (userTasksCount > 1) ? "s" : "";
            System.out.println("\tYou now have " + userTasksCount + " task" + addS + " left in your list!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tTask does not exist!");
        }
    }

    public static void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
    }

    public static void printExitMessage() {
        System.out.println(MESSAGE_EXIT);
    }
}
