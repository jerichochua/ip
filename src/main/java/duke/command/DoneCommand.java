package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DoneCommand extends Command {
    public static final String COMMAND_DONE = "done";
    private final int taskNumber;

    public DoneCommand(String arguments) {
        taskNumber = Integer.parseInt(arguments);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markTaskAsDone(taskNumber);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
