package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Terminates the program.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_BYE = "bye";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
    }

    public boolean isExit() {
        return true;
    }
}
