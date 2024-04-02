package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DateTimeFieldTest {

    @Test
    void isValidDateTimeField_returnTrue() {
        assertTrue(DateTimeField.isValidDateTimeField("2022-12-12 08:00"));
        assertTrue(DateTimeField.isValidDateTimeField("2020-02-29 23:59"));
    }

    @Test
    void isValidDateTimeField_returnFalse() {
        assertFalse(DateTimeField.isValidDateTimeField("2022-12-1 08:00"));
        assertFalse(DateTimeField.isValidDateTimeField("2022-12-12 08:00:00"));
        assertFalse(DateTimeField.isValidDateTimeField("2022-1-12 08:00"));
        assertFalse(DateTimeField.isValidDateTimeField("2022-12-12 8:00"));
    }
}
