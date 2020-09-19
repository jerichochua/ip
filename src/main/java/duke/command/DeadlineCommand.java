package duke.command;

import duke.tasklist.TaskList;

public class DeadlineCommand extends Command {
    public static final String COMMAND_DEADLINE = "deadline";
    private final String arguments;

    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addDeadline(arguments);
    }
}
