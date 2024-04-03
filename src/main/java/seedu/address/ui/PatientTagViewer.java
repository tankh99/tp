package seedu.address.ui;

import java.util.Comparator;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.ReadOnlyPatientList;

/**
 * Represents a class that displays all patient tags in main UI.
 */
public class PatientTagViewer extends UiPart<Region> {
    public static final int MAX_TAGS_TO_BE_DISPLAYED = 20;

    private static final String FXML = "PatientTagViewer.fxml";

    @FXML
    private FlowPane tags;

    /**
     * Renders the Patient alias as  Labels
     * @param model which contains all patient information.
     */
    public PatientTagViewer(ReadOnlyPatientList model) {
        super(FXML);

        // Create as a HashMap to store the tagInformation as key and count as value
        HashMap<String, Integer> tagInfo = new HashMap<>();

        // Iterate all person, and their tag to count how many occurrence.
        model.getPersonList().stream().forEach(person -> {
            person.getTags().stream().forEach(tag -> {
                tagInfo.put(tag.tagName, tagInfo.getOrDefault(tag.tagName, 0) + 1);
            });
        });


        tagInfo.entrySet().stream()
                // Sort by value size in descending order
                // Credits: ChatGPT "Stream sort by value size"
                .sorted(Comparator.comparingInt(entry -> -entry.getValue()))
                // Limit length to prevent malicious users.
                .limit(MAX_TAGS_TO_BE_DISPLAYED).forEach(tag -> {
                    addLabel(tag.getKey() + " (" + tag.getValue() + ")");
                }
        );
    }




    private void addLabel(String labelText) {
        Label label = new Label(labelText);
        tags.getChildren().add(label);
    }
}
