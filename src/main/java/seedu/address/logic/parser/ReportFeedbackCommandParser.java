package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FROM_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TO_DATE;

import java.time.LocalDateTime;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.ReportFeedbackCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ReportFeedbackCommandParser implements Parser<ReportFeedbackCommand> {
    @Override
    public ReportFeedbackCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FROM_DATE, PREFIX_TO_DATE);
        if (!ParserUtil.hasAtLeastOnePrefixPresent(argMultimap, PREFIX_FROM_DATE, PREFIX_TO_DATE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ReportFeedbackCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_FROM_DATE, PREFIX_TO_DATE);
        LocalDateTime fromDate = ParserUtil.parseDate(
                argMultimap.getValue(PREFIX_FROM_DATE).orElse(""), true);
        LocalDateTime toDate = ParserUtil.parseDate(
                argMultimap.getValue(PREFIX_TO_DATE).orElse(""), false);

        if (fromDate != null && toDate != null && toDate.isBefore(fromDate)) {
            throw new ParseException(Messages.MESSAGE_TO_DATE_BEFORE_FROM_DATE);
        }
        return new ReportFeedbackCommand(fromDate, toDate);
    }
}
