package duke.command;

import duke.exception.EmptyDescriptionException;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeadlineCommand extends Command {
    public static final String COMMAND_DEADLINE = "deadline";
    private final String arguments;

    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] argumentSplit = arguments.split(" /by ");

        try {
            Task deadline = tasks.addDeadline(argumentSplit);
            ui.printTaskAdded(deadline);
            ui.printRemainingTasks(tasks);
        } catch (EmptyDescriptionException e) {
            ui.printToUser("\tDescription or deadline cannot be empty!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
