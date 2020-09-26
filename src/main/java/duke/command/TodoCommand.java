package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Adds a todo task to the task list.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_TODO = "todo";
    private final String arguments;

    public TodoCommand(String arguments) {
        this.arguments = arguments.trim();
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task todo = tasks.addTodo(arguments);
            ui.printTaskAdded(todo);
            ui.printRemainingTasks(tasks);
            storage.writeToFile(tasks);
        } catch (EmptyDescriptionException e) {
            ui.printToUser("\tDescription cannot be empty!");
        } catch (IOException e) {
            ui.printFileError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
