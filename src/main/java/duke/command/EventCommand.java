package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.storage.Storage;
import duke.task.Task;
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

        try {
            Task event = tasks.addEvent(argumentSplit);
            ui.printTaskAdded(event);
            ui.printRemainingTasks(tasks);
        } catch (EmptyDescriptionException e) {
            ui.printToUser("\tDescription or event date/time cannot be empty!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
