package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    public static final String COMMAND_DELETE = "delete";
    private final int taskNumber;

    public DeleteCommand(String arguments) {
        taskNumber = Integer.parseInt(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.deleteTask(taskNumber);
            ui.printTaskDeleted(task);
            ui.printRemainingTasks(tasks);
        } catch (DukeException e) {
            ui.printToUser("\tTask does not exist!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
