package seedu.address.model.patient;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Tags} (case-insensitive) matches a part of the keywords given.
 */
public class TagContainsKeywordPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public TagContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Checks if the given patient has any tags that contain the specified keywords.
     *
     * @param patient the patient to be checked
     * @return true if the patient has any tags that contain the specified keywords (case-insensitive), false otherwise
     */
    @Override
    public boolean test(Patient patient) {
        return keywords.stream()
                .anyMatch(keyword ->
                        patient.getTags().stream()
                                .anyMatch(tag -> tag.tagName.equalsIgnoreCase(keyword))
                );
    }

    /**
     * Checks if the current object is equal to the given object.
     *
     * @param other The object to compare against
     * @return True if the objects are equal, false otherwise
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagContainsKeywordPredicate)) {
            return false;
        }

        TagContainsKeywordPredicate otherTagContainsKeywordsPredicate = (TagContainsKeywordPredicate) other;
        return keywords.equals(otherTagContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
