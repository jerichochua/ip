package duke.command;

import duke.exception.IllegalIndexException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {
    public static final String COMMAND_DELETE = "delete";
    private final int taskNumber;

    public DeleteCommand(String arguments) {
        taskNumber = Integer.parseInt(arguments.trim());
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.deleteTask(taskNumber);
            ui.printTaskDeleted(task);
            ui.printRemainingTasks(tasks);
            storage.writeToFile(tasks);
        } catch (IllegalIndexException e) {
            ui.printToUser("\tTask does not exist!");
        } catch (IOException e) {
            ui.printFileError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
