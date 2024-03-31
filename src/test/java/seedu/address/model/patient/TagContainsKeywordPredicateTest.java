package seedu.address.model.patient;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TagContainsKeywordPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("depression");
        List<String> secondPredicateKeywordList = Arrays.asList("depression", "sadness");

        TagContainsKeywordPredicate firstPredicate = new TagContainsKeywordPredicate(firstPredicateKeywordList);
        TagContainsKeywordPredicate secondPredicate = new TagContainsKeywordPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordPredicate firstPredicateCopy =
                new TagContainsKeywordPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagListContainsTag_returnsTrue() {
        // One keyword
        TagContainsKeywordPredicate predicate = new TagContainsKeywordPredicate(Collections.singletonList("noIntern"));
        assertTrue(predicate.test(new PersonBuilder().withTags("noIntern").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordPredicate(Arrays.asList("depression", "intern"));
        assertTrue(predicate.test(new PersonBuilder().withTags("depression", "noIntern", "sadness").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordPredicate(Arrays.asList("depression", "jobless", "relationship"));
        assertTrue(predicate.test(new PersonBuilder().withTags("anxiety", "sadness", "depression").build()));


        // Only one matching keyword - non case-sensitive
        predicate = new TagContainsKeywordPredicate(Arrays.asList("dEpRESsION"));
        assertTrue(predicate.test(new PersonBuilder().withTags("anxiety", "sadness", "depression").build()));
    }

    @Test
    public void test_tagListDoesNotContainTag_returnsFalse() {
        // Zero keywords
        TagContainsKeywordPredicate predicate = new TagContainsKeywordPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("noIntern").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordPredicate(Arrays.asList("Carol"));
        assertFalse(predicate.test(new PersonBuilder().withTags("depression", "noIntern", "sadness").build()));

        // Keywords match name, email but does not match phone
        predicate = new TagContainsKeywordPredicate(Arrays.asList("12345", "jjjj", "Main", "Street"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("999")
                .withTags("anxiety", "sadness", "depression")
                .withEmail("alice@gmail.com").withId(12).build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        TagContainsKeywordPredicate predicate = new TagContainsKeywordPredicate(keywords);

        String expected = TagContainsKeywordPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}