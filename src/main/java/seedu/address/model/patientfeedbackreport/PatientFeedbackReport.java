package seedu.address.model.patientfeedbackreport;

import seedu.address.model.patient.Name;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Models the data required for a patient feedback report.
 */
public class PatientFeedbackReport implements Comparable<PatientFeedbackReport> {

    private Name patientName;
    private double avgFeedbackScore;

    /**
     * Creates a new PatientFeedbackReport object.
     * @param patientName
     * @param avgFeedbackScore
     */
    public PatientFeedbackReport(Name patientName, double avgFeedbackScore) {
        requireAllNonNull(patientName, avgFeedbackScore);
        this.patientName = patientName;
        this.avgFeedbackScore = avgFeedbackScore;
    }

    /**
     * Sort by feedback score in descending order
     * @param o the object to be compared.
     * @return
     */
    @Override
    public int compareTo(PatientFeedbackReport o) {
        if (avgFeedbackScore > o.avgFeedbackScore) {
            return -1;
        } else if (avgFeedbackScore < o.avgFeedbackScore) {
            return 1;
        } else {
            return o.patientName.fullName.compareTo(patientName.fullName);
        }
    }
}
