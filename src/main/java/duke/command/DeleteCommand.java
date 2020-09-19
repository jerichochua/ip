package duke.command;

import duke.storage.Storage;
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
        tasks.deleteTask(taskNumber);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
