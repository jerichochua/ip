package duke.command;

import duke.storage.Storage;
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
        tasks.addDeadline(argumentSplit);
        ui.printRemainingTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
