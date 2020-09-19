package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
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
        Task task;

        try {
            task = tasks.markTaskAsDone(taskNumber);
            ui.printTaskDone(task);
        } catch (DukeException e) {
            ui.printToUser("\tInvalid task number entered!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
