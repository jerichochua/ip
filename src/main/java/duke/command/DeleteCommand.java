package duke.command;

import duke.tasklist.TaskList;

public class DeleteCommand extends Command {
    public static final String COMMAND_DELETE = "delete";
    private final int taskNumber;

    public DeleteCommand(String arguments) {
        taskNumber = Integer.parseInt(arguments);
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.deleteTask(taskNumber);
    }
}
