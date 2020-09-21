package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes a command by the user.
     *
     * @param tasks contains the user's tasks
     * @param ui handles the printing of messages to the user
     * @param storage handles the storing of the user's tasks in a file
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * @return a Boolean flag to determine whether to exit the application
     */
    public abstract boolean isExit();
}
