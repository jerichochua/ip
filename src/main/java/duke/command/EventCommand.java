package duke.command;

import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {
    public static final String COMMAND_EVENT = "event";
    private final String arguments;

    public EventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] argumentSplit = arguments.split(" /at ");
        tasks.addEvent(argumentSplit);
        ui.printRemainingTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
