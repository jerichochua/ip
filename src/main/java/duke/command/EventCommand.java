package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

public class EventCommand extends Command {
    public static final String COMMAND_EVENT = "event";
    private final String argumentString;

    public EventCommand(String argumentString) {
        this.argumentString = argumentString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] argumentSplit = argumentString.split(" /at ");

        try {
            Task event = tasks.addEvent(argumentSplit);
            ui.printTaskAdded(event);
            ui.printRemainingTasks(tasks);
            storage.writeToFile(tasks);
        } catch (EmptyDescriptionException e) {
            ui.printToUser("\tDescription or event date/time cannot be empty!");
        } catch (IOException e) {
            ui.printFileError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
