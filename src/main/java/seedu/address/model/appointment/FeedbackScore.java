package seedu.address.model.appointment;

import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Appointment's feedback score in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidScore(int)}
 */
public class FeedbackScore {

    public static final String MESSAGE_CONSTRAINTS = "Feedback score should only be from 1 to 5 (inclusive)";
    public final Integer feedbackScore;

    /**
     * Constructs a {@code FeedbackScore}.
     *
     * @param feedbackScore A valid feedback score.
     */
    public FeedbackScore(Integer feedbackScore) {

        checkArgument(isValidScore(feedbackScore), MESSAGE_CONSTRAINTS);
        this.feedbackScore = feedbackScore;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidScore(Integer test) {
        if (test == null) {
            return true;
        }
        return test >= 1 && test <= 5;
    }

    public int getFeedbackScore() {
        return feedbackScore;
    }

    @Override
    public String toString() {
        return String.valueOf(feedbackScore);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof FeedbackScore)) {
            return false;
        }

        FeedbackScore otherScore = (FeedbackScore) other;
        return feedbackScore == otherScore.feedbackScore;
    }

}
