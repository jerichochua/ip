package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class ListCommand extends Command {
    public static final String COMMAND_LIST = "list";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printToUser("\tHere are your tasks:");
        ui.printAllTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
