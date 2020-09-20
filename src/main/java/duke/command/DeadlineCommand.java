package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Adds a deadline task to the task list.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_DEADLINE = "deadline";
    private final String argumentString;

    public DeadlineCommand(String argumentString) {
        this.argumentString = argumentString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] argumentSplit = argumentString.split(" /by ");

        try {
            Task deadline = tasks.addDeadline(argumentSplit);
            ui.printTaskAdded(deadline);
            ui.printRemainingTasks(tasks);
            storage.writeToFile(tasks);
        } catch (EmptyDescriptionException e) {
            ui.printToUser("\tDescription or deadline cannot be empty!");
        } catch (IOException e) {
            ui.printFileError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
