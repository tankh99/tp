package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ALIAS = new Prefix("a/");
    public static final Prefix PREFIX_PATIENT_ID = new Prefix("pid/");
    public static final Prefix PREFIX_START_DATETIME = new Prefix("sd/");
    public static final Prefix PREFIX_END_DATETIME = new Prefix("ed/");
    public static final Prefix PREFIX_ATTEND = new Prefix("att/");
    public static final Prefix PREFIX_APPOINTMENT_DESCRIPTION = new Prefix("ad/");
    public static final Prefix PREFIX_APPOINTMENT_ID = new Prefix("aid/");
    public static final Prefix PREFIX_FEEDBACK_SCORE = new Prefix("s/");

}
