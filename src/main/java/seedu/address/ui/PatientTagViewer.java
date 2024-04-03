package seedu.address.ui;

import javafx.fxml.FXML;


import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;

public class PatientTagViewer extends UiPart<Region> {
    private static final String FXML = "PatientTagViewer.fxml";

    @FXML
    private FlowPane tags;

    public PatientTagViewer() {
        super(FXML);

        // Set horizontal and vertical gaps
        tags.setHgap(10); // Set the horizontal gap between children
        tags.setVgap(10); // Set the vertical gap between children


        // TODO: Limit to 10
        addStyledLabel("depression");
        addStyledLabel("anxiety");
        addStyledLabel("substance abuse");
        addStyledLabel("PTSD");
        addStyledLabel("OCD");
        addStyledLabel("bipolar disorder");
        addStyledLabel("schizophrenia");
        addStyledLabel("ADHD");
        addStyledLabel("eating disorders");
        addStyledLabel("insomnia");
    }


    // Method to add styled label
    private void addStyledLabel(String labelText) {
        Label label = new Label(labelText);
        label.getStyleClass().add("cell_small_label"); // Add a CSS class for styling
        tags.getChildren().add(label);
    }
}
