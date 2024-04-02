package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END_DATETIME;

import java.time.LocalDateTime;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ReportFeedbackCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ReportFeedbackCommand object
 */
public class ReportFeedbackCommandParser implements Parser<ReportFeedbackCommand> {
    @Override
    public ReportFeedbackCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_START_DATETIME, PREFIX_END_DATETIME);
        if (!ParserUtil.hasAtLeastOnePrefixPresent(argMultimap, PREFIX_START_DATETIME, PREFIX_END_DATETIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReportFeedbackCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_START_DATETIME, PREFIX_END_DATETIME);

        // If no values are provided in the command, We set the default values for fromDate and toDate to
        // LocalDateTime.MIN and LocalDateTime.MAX respectively
        LocalDateTime fromDate;
        LocalDateTime toDate;
        if (argMultimap.getValue(PREFIX_START_DATETIME).isEmpty()) {
            fromDate = LocalDateTime.MIN;
        } else {
            fromDate = ParserUtil.parseDate(
                    argMultimap.getValue(PREFIX_START_DATETIME).get(), true);
        }
        if (argMultimap.getValue(PREFIX_END_DATETIME).isEmpty()) {
            toDate = LocalDateTime.MAX;
        } else {
            toDate = ParserUtil.parseDate(
                argMultimap.getValue(PREFIX_END_DATETIME).get(), false);
        }

        if (fromDate != null && toDate != null && toDate.isBefore(fromDate)) {
            throw new ParseException(Messages.MESSAGE_INVALID_START_END_DATETIME);
        }
        return new ReportFeedbackCommand(fromDate, toDate);
    }
}
