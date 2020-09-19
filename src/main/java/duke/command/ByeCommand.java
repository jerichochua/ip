package duke.command;

import duke.tasklist.TaskList;

public class ByeCommand extends Command {
    public static final String COMMAND_BYE = "bye";

    @Override
    public void execute(TaskList tasks) {
    }

    public boolean isExit() {
        return true;
    }
}
