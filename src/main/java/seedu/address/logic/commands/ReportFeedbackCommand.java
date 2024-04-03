package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;

import java.time.LocalDateTime;

import seedu.address.commons.util.DateUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.EndDateTime;
import seedu.address.model.appointment.FilterAppointmentPredicate;
import seedu.address.model.appointment.StartDateTime;


/**
 * Prints out feedback score statistics for each patient across a specific date range
 */
public class ReportFeedbackCommand extends Command {

    public static final String COMMAND_WORD = "reportf";

    public static final String MESSAGE_SUCCESS = "Generated patient feedback report from %1$s to %2$s";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Generates a report of feedback scores "
            + "for each patient across a specific date range. "
            + "Parameters: "
            + PREFIX_START_DATETIME + "FROM_DATE "
            + PREFIX_END_DATETIME + " TO_DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_START_DATETIME + "2024-03-18 "
            + PREFIX_END_DATETIME + "2024-03-18\n";

    private final LocalDateTime fromDate;
    private final LocalDateTime toDate;

    // Note that we're unable to perform the same predicate query logic here because reports are fundmantally different
    // from the usual model objects as report objects are transient data that are aggregated from other models

    /**
     * Creates a ReportFeedbackCommand to generate a report of feedback scores for each patient across a specific date
     * @param fromDate
     * @param toDate
     */
    public ReportFeedbackCommand(LocalDateTime fromDate, LocalDateTime toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredAppointmentList(new FilterAppointmentPredicate(
            new StartDateTime(fromDate), new EndDateTime(toDate)));
        String fromDateStr = DateUtil.formatDate(fromDate.toLocalDate());
        if (fromDate == LocalDateTime.MIN) {
            fromDateStr = "the beginning of time";
        }
        String toDateStr = DateUtil.formatDate(toDate.toLocalDate());
        if (toDate == LocalDateTime.MAX) {
            toDateStr = "the end of time";
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, fromDateStr, toDateStr));
    }
}
