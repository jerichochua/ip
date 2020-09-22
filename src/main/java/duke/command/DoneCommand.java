package duke.command;

import duke.exception.IllegalIndexException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Sets a task as done in the task list.
 */
public class DoneCommand extends Command {
    public static final String COMMAND_DONE = "done";
    private final int taskNumber;

    public DoneCommand(String argumentString) {
        taskNumber = Integer.parseInt(argumentString);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.markTaskAsDone(taskNumber);
            ui.printTaskDone(task);
            storage.writeToFile(tasks);
        } catch (IllegalIndexException e) {
            ui.printToUser("\tInvalid task number entered!");
        } catch (IOException e) {
            ui.printFileError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
