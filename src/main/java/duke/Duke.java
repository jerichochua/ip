package duke;

import duke.exception.EmptyDescriptionException;
import duke.exception.IllegalCommandException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class Duke {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    private static Storage storage;
    private static TaskList tasks;
    private static Ui ui;

    public static void main(String[] args) {
        String userInput;

        ui = new Ui();
        tasks = new TaskList();

        ui.printWelcomeMessage();

        try {
            storage = new Storage();
            storage.readFile(tasks);
        } catch (IOException e) {
            ui.printToUser("Error: The file cannot be opened or created!");
        }

        userInput = ui.getUserInput();

        while (!userInput.equals(COMMAND_BYE)) {
            processUserInput(userInput);
            userInput = ui.getUserInput();
        }

        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            ui.printToUser("Error: The file cannot be written to!");
        }

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
}
