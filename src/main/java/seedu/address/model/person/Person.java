package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

import java.util.*;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.parser.Prefix;
import seedu.address.model.tag.Tag;
import seedu.address.storage.JsonAddressBookStorage;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {
    private static final Logger logger = LogsCenter.getLogger(Person.class);
    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Remark remark;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Remark remark) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.remark = remark;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Remark getRemark() {
        return remark;
    }
    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && remark.equals(otherPerson.remark)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, remark, tags);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("remark", remark)
                .add("tags", tags)
                .toString();
    }

    /**
     * Creates a new instance of the current person object and updates only specified values in the new
     * @return
     */
    public Person toNewPerson(Map<Prefix, Object> editedValues) {
        Name name = this.name;
        Phone phone = this.phone;
        Email email = this.email;
        Address address = this.address;
        Set<Tag> tags = this.tags;
        Remark remark = this.remark;
        for (Map.Entry<Prefix, Object> entry: editedValues.entrySet()) {
            Object value = entry.getValue();
            Prefix key = entry.getKey();
            if (key == PREFIX_NAME) {
                name = (Name) value;
            } else if (key == PREFIX_PHONE) {
                phone = (Phone) value;
            } else if (key == PREFIX_EMAIL) {
                email = (Email) value;
            } else if (key == PREFIX_ADDRESS) {
                address = (Address) value;
            } else if (key == PREFIX_TAG) {
                tags = new HashSet<Tag>();
                tags.add((Tag) value);
            } else if (key == PREFIX_REMARK) {
                remark = (Remark) value;
            } else {
                logger.severe("Received an unknown prefix " + key + " with value " + value);
            }
        }
        return new Person(name, phone, email, address, tags, remark);
    }
}
