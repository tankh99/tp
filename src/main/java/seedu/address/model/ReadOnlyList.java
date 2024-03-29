package seedu.address.model;

import javafx.collections.ObservableList;

/**
 * Represents a observable list that is read only.
 */
public interface ReadOnlyList<T> {
    ObservableList<T> getList();
}
