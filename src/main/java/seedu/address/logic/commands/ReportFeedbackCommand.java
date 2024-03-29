package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO_DATE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.ReportFeedbackCommandParser;
import seedu.address.model.Model;

import java.time.LocalDateTime;


/**
 * Prints out feedback score statistics for each patient across a specific date range
 */
public class ReportFeedbackCommand extends Command {

    public static final String COMMAND_WORD = "reportfeedback";

    public static final String MESSAGE_SUCCESS = "Generated report from %1$s to %2$s";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generates a report of feedback scores "
            + "for each patient across a specific date range. "
            + "Parameters: "
            + PREFIX_FROM_DATE + "FROM_DATE "
            + PREFIX_TO_DATE + " TO_DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_FROM_DATE + "2024-03-18 "
            + PREFIX_TO_DATE + "2024-03-18\n"
            + "Note: At least from date or to date must be specified";

    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    public ReportFeedbackCommand(LocalDateTime fromDate, LocalDateTime toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        // TODO: Create a function inside model that updates the reports UI
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
