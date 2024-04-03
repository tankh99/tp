package seedu.address.ui;

import javafx.fxml.FXML;


import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyPatientList;

import java.util.Comparator;
import java.util.HashMap;

public class PatientTagViewer extends UiPart<Region> {
    private static final String FXML = "PatientTagViewer.fxml";

    public static final int MAX_TAGS_TO_BE_DISPLAYED = 20;

    @FXML
    private FlowPane tags;

    public PatientTagViewer(ReadOnlyPatientList model) {
        super(FXML);

        // Set horizontal and vertical gaps
        tags.setHgap(10); // Set the horizontal gap between children
        tags.setVgap(10); // Set the vertical gap between children



        HashMap<String, Integer> tagInfo = new HashMap<>();

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
                    addStyledLabel(tag.getKey() + " (" + tag.getValue() + ")");
                }
        );
    }




    // Method to add styled label
    private void addStyledLabel(String labelText) {
        Label label = new Label(labelText);
        tags.getChildren().add(label);
    }
}
