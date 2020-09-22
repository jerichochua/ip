package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Finds tasks with a specified keyword in the task list.
 */
public class FindCommand extends Command {
    public static final String COMMAND_FIND = "find";
    private final String keywords;

    public FindCommand(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTasks = tasks.filterByKeywords(keywords);
        ui.printFilteredTasks(filteredTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
