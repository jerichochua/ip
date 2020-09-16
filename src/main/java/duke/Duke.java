package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.IllegalCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String FILE_PATH = "data/duke.txt";
    private static final String DIR_PATH = "data";

    private static TaskList tasks;
    private static Ui ui;

    public static void main(String[] args) {
        String userInput;

        ui = new Ui();
        tasks = new TaskList();

        ui.printWelcomeMessage();

        try {
            createFileAndDir();
            readFile();
        } catch (IOException e) {
            ui.printToUser("Error: something happened with the file...");
        }

        userInput = ui.getUserInput();

        while (!userInput.equals(COMMAND_BYE)) {
            processUserInput(userInput);
            userInput = ui.getUserInput();
        }

        tasks.addTasksToFile();
        ui.printExitMessage();
    }

    public static void processUserInput(String userInput) {
        int taskNumber;

        if (userInput.equals(COMMAND_LIST)) {
            tasks.printUserTasks();
            return;
        }

        try {
            String userCommand = userInput.substring(0, userInput.indexOf(" "));
            String arguments = userInput.substring(userInput.indexOf(" ") + 1);
            switch (userCommand) {
            case COMMAND_TODO:
                tasks.addTodo(arguments);
                break;
            case COMMAND_DEADLINE:
                tasks.addDeadline(arguments);
                break;
            case COMMAND_EVENT:
                tasks.addEvent(arguments);
                break;
            case COMMAND_DONE:
                taskNumber = Integer.parseInt(arguments);
                tasks.markTaskAsDone(taskNumber);
                break;
            case COMMAND_DELETE:
                taskNumber = Integer.parseInt(arguments);
                tasks.deleteTask(taskNumber);
                break;
            default:
                throw new IllegalCommandException();
            }
        } catch (IllegalCommandException e) {
            ui.printToUser("\tWrong command entered!");
        } catch (StringIndexOutOfBoundsException | EmptyDescriptionException e) {
            ui.printToUser("\tDescription cannot be empty!");
        } catch (NullPointerException | NumberFormatException e) {
            ui.printToUser("\tInvalid task number entered!");
        }
    }

    public static void createFileAndDir() throws IOException {
        File dir = new File(DIR_PATH);
        File file = new File(FILE_PATH);

        if (!dir.exists() || !file.exists()) {
            dir.mkdir();
            file.createNewFile();
        }
    }

    public static void readFile() throws IOException {
        File file = new File(FILE_PATH);
        String task;
        String[] arguments;

        if (file.exists()) {
            Scanner readFile = new Scanner(file);
            while (readFile.hasNext()) {
                task = readFile.nextLine();
                arguments = task.split(" \\| ");
                processFileInput(arguments);
            }
        }
    }

    public static void processFileInput(String[] arguments) {
        String typeOfTask = arguments[0];

        switch (typeOfTask) {
        case "T":
            tasks.addTask(new Todo(arguments[2]));
            break;
        case "D":
            tasks.addTask(new Deadline(arguments[2], arguments[3]));
            break;
        case "E":
            tasks.addTask(new Event(arguments[2], arguments[3]));
            break;
        }

        tasks.checkTaskStatus(arguments[1]);
    }
}
