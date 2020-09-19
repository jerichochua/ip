package duke.command;

import duke.tasklist.TaskList;

public class ListCommand extends Command {
    public static final String COMMAND_LIST = "list";

    @Override
    public void execute(TaskList tasks) {
        tasks.printUserTasks();
    }
}
