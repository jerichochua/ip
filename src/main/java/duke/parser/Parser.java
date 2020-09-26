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
import duke.exception.IllegalDescriptionException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses the user's input.
 */
public class Parser {
    public static final Pattern COMMAND_FORMAT = Pattern.compile("(?<command>\\S+)(?<arguments>.*)");

    /**
     * Parses the user's input into a command.
     *
     * @param userInput user's input string
     * @return a Command object to be executed
     * @throws IllegalCommandException if the command entered does not exist
     * @throws IllegalDescriptionException if the description entered contains illegal characters
     */
    public Command parseUserInput(String userInput) throws IllegalCommandException, IllegalDescriptionException {
        Matcher matcher = COMMAND_FORMAT.matcher(userInput.trim());

        if (!matcher.matches()) {
            throw new IllegalCommandException();
        }

        String userCommand = matcher.group("command").toLowerCase();
        String arguments = matcher.group("arguments");

        if (arguments.contains("|")) {
            throw new IllegalDescriptionException();
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
