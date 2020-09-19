package duke.command;

import duke.tasklist.TaskList;

public abstract class Command {
    public abstract void execute(TaskList tasks);
}
