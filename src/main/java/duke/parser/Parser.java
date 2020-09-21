package duke.parser;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.IllegalCommandException;

public class Parser {
    public Command parseUserInput(String userInput) throws IllegalCommandException {
        String userCommand, arguments = "";

        if (userInput.contains(" ")) {
            userCommand = userInput.substring(0, userInput.indexOf(" "));
            arguments = userInput.substring(userInput.indexOf(" ") + 1);
        } else {
            userCommand = userInput;
        }

        switch (userCommand) {
        case ListCommand.COMMAND_LIST:
            return new ListCommand();
        case ByeCommand.COMMAND_BYE:
            return new ByeCommand();
        case TodoCommand.COMMAND_TODO:
            return new TodoCommand(arguments);
        case DeadlineCommand.COMMAND_DEADLINE:
            return new DeadlineCommand(arguments);
        case EventCommand.COMMAND_EVENT:
            return new EventCommand(arguments);
        case DoneCommand.COMMAND_DONE:
            return new DoneCommand(arguments);
        case DeleteCommand.COMMAND_DELETE:
            return new DeleteCommand(arguments);
        case FindCommand.COMMAND_FIND:
            return new FindCommand(arguments);
        default:
            throw new IllegalCommandException();
        }
    }
}
