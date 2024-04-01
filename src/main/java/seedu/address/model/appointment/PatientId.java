package seedu.address.model.appointment;

import java.util.List;

import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.patient.Patient;

/**
 * Represents an Appointment's patient id in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidId(int)}
 */
public class PatientId {
    public static final String MESSAGE_CONSTRAINTS = "No such patient with this id exists. Please pick a valid id.";
    public final int patientId;

    /**
     * Constructs a {@code PatientId}.
     *
     * @param patientId A valid patient id.
     */
    public PatientId(int patientId) {
        checkArgument(isValidId(patientId), MESSAGE_CONSTRAINTS);
        this.patientId = patientId;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidId(int test) {
        return test >= 1;
    }

    public static boolean isValidId(int test, List<Patient> patients) {
        return patients.stream().anyMatch(patient -> patient.getSid() == test);
    }

    @Override
    public String toString() {
        return String.valueOf(patientId);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientId)) {
            return false;
        }

        PatientId otherId = (PatientId) other;
        return patientId == otherId.patientId;
    }
}
