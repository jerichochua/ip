package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final int MAX_SIZE = 100;
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String MESSAGE_WELCOME = "Hello! I'm Duke" + System.lineSeparator() + "What can I do for you?";
    private static final String MESSAGE_EXIT = "Bye. Hope to see you again soon!";
    private static final String FILE_PATH = "data/duke.txt";
    private static final String DIR_PATH = "data";
    private static Task[] userTasks = new Task[MAX_SIZE];
    private static int userTasksCount = 0;

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);

        printWelcomeMessage();

        try {
            createFileAndDir();
        } catch (IOException e) {
            System.out.println("Error: something happened with the file...");
        }

        userInput = in.nextLine();

        while (!userInput.equals(COMMAND_BYE)) {
            processUserInput(userInput);
            userInput = in.nextLine();
        }

        addTasksToFile();
        printExitMessage();
    }

    public static void processUserInput(String userInput) {
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
                int taskNumber = Integer.parseInt(arguments);
                markTaskAsDone(taskNumber);
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
        userTasks[userTasksCount] = new Todo(description);
        addTaskSuccess();
    }

    public static void addDeadline(String arguments) {
        String[] argumentSplit = arguments.split(" /by ");
        try {
            userTasks[userTasksCount] = new Deadline(argumentSplit[0], argumentSplit[1]);
            addTaskSuccess();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tDescription or deadline cannot be empty!");
        }
    }

    public static void addEvent(String arguments) {
        String[] argumentSplit = arguments.split(" /at ");
        try {
            userTasks[userTasksCount] = new Event(argumentSplit[0], argumentSplit[1]);
            addTaskSuccess();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\tDescription or event date/time cannot be empty!");
        }
    }

    public static void addTaskSuccess() {
        System.out.println("\tAdded: " + userTasks[userTasksCount]);
        userTasksCount++;
        String addS = (userTasksCount > 1) ? "s" : "";
        System.out.println("\tYou now have " + userTasksCount + " task" + addS + " in your list.");
    }

    public static void listUserTasks(int taskCount) {
        System.out.println("\tHere are your tasks:");
        for (int i = 1; i <= taskCount; i++) {
            System.out.format("\t%d. %s\n", i, userTasks[i - 1]);
        }
    }

    public static void markTaskAsDone(int taskNumber) {
        userTasks[taskNumber - 1].markAsDone();
        System.out.println("\tI have marked the following task as done:");
        System.out.format("\t\t%s\n", userTasks[taskNumber - 1]);
    }

    public static void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
    }

    public static void printExitMessage() {
        System.out.println(MESSAGE_EXIT);
    }

    public static void createFileAndDir() throws IOException {
        File dir = new File(DIR_PATH);
        File file = new File(FILE_PATH);

        if (!dir.exists() || !file.exists()) {
            dir.mkdir();
            file.createNewFile();
        }
    }

    public static void addToFile(String data) throws IOException {
        FileWriter file = new FileWriter(FILE_PATH, true);
        file.write(data + System.lineSeparator());
        file.close();
    }

    public static void addTasksToFile() {
        Task task;
        try {
            for (int i = 1; i <= userTasksCount; i++) {
                task = userTasks[i - 1];
                if (task.getClass() == Todo.class) {
                    addToFile(String.format("T | %s | %s", task.getStatusIcon(), task.getTaskDescription()));
                } else if (task.getClass() == Deadline.class) {
                    addToFile(String.format("D | %s | %s | %s", task.getStatusIcon(), task.getTaskDescription(), ((Deadline) task).getBy()));
                } else if (task.getClass() == Event.class) {
                    addToFile(String.format("E | %s | %s | %s", task.getStatusIcon(), task.getTaskDescription(), ((Event) task).getEventAt()));
                }
            }
        } catch (IOException e) {
            System.out.println("Something happened with the file creation...");
        }
    }
}
