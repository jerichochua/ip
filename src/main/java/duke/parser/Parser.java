package duke.parser;

import duke.command.ByeCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.IllegalCommandException;

/**
 * Parses the user's input.
 */
public class Parser {
    /**
     * Parses the user's input into a command.
     *
     * @param userInput user's input string
     * @return a Command object to be executed
     * @throws IllegalCommandException if the command entered does not exist
     */
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
        case ClearCommand.COMMAND_CLEAR:
            return new ClearCommand();
        default:
            throw new IllegalCommandException();
        }
    }
}
