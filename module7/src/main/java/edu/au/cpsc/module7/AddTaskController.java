package edu.au.cpsc.module7;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

public class AddTaskController {

    private MainController mainController; // ⭐ Connects to MainController

    @FXML
    private TextField taskNameField;

    @FXML
    private DatePicker dueDatePicker;

    @FXML
    private ComboBox<String> priorityBox;

    @FXML
    private Button saveButton, cancelButton;

    public void setMainController(MainController controller) {
        this.mainController = controller;
    }

    @FXML
    public void initialize() {
        priorityBox.setItems(FXCollections.observableArrayList("Low", "Medium", "High"));
    }

    @FXML
    private void saveTask() {
        String task = taskNameField.getText();
        if (!task.isEmpty() && dueDatePicker.getValue() != null && priorityBox.getValue() != null) {
            String taskText = task + " (Due: " + dueDatePicker.getValue() + ", Priority: " + priorityBox.getValue() + ")";

            if (mainController != null) {
                javafx.application.Platform.runLater(() -> {
                    mainController.taskList.getItems().add(taskText);
                });
            } else {
                System.out.println("ERROR: MainController is NULL!");
            }

            closeWindow();
        }
    }


    @FXML
    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
