package seedu.address.model.patient;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

import java.util.List;
import java.util.function.Predicate;

public class TagContainsKeywordPredicate implements Predicate<Patient> {
    private final List<String> keywords;

    public TagContainsKeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

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
     *w
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
