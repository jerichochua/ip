package duke.parser;

import duke.exception.EmptyDescriptionException;
import duke.exception.IllegalCommandException;
import duke.tasklist.TaskList;

public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";

    public void processUserInput(TaskList tasks, String userInput) throws IllegalCommandException, EmptyDescriptionException {
        int taskNumber;

        if (userInput.equals(COMMAND_LIST)) {
            tasks.printUserTasks();
            return;
        } else if (userInput.equals(COMMAND_BYE)) {
            return;
        }

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
    }
}
