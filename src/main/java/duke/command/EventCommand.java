package duke.command;

import duke.tasklist.TaskList;

public class EventCommand extends Command {
    public static final String COMMAND_EVENT = "event";
    private final String arguments;

    public EventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.addEvent(arguments);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
