package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class ClearCommand extends Command {
    public static final String COMMAND_CLEAR = "clear";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.clearTaskList();
            ui.printTasksCleared();
            storage.writeToFile(tasks);
        } catch (IOException e) {
            ui.printFileError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
