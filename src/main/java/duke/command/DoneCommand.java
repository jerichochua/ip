package duke.command;

import duke.tasklist.TaskList;

public class DoneCommand extends Command {
    public static final String COMMAND_DONE = "done";
    private final int taskNumber;

    public DoneCommand(String arguments) {
        taskNumber = Integer.parseInt(arguments);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.markTaskAsDone(taskNumber);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
