package edu.au.cpsc.module7;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainController {

    @FXML
    public ListView<String> taskList;

    @FXML
    private MenuItem menuNewTask, menuExit;

    @FXML
    private Button deleteButton, completeButton;

    @FXML
    public void initialize() {
        menuNewTask.setAccelerator(KeyCombination.keyCombination("Ctrl+N"));
        menuExit.setAccelerator(KeyCombination.keyCombination("Ctrl+Q"));
    }

    @FXML
    private void openAddTaskWindow() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/edu/au/cpsc/module7/addtask-view.fxml"));
        Parent root = loader.load();

        AddTaskController addTaskController = loader.getController();
        addTaskController.setMainController(this);

        Stage parentStage = (Stage) taskList.getScene().getWindow();

        Stage stage = new Stage();
        stage.setTitle("Add Task");
        stage.initOwner(parentStage);
        stage.initModality(javafx.stage.Modality.NONE);

        Scene scene = new Scene(root);


        stage.setWidth(parentStage.getWidth());
        stage.setHeight(parentStage.getHeight());

        stage.setScene(scene);
        stage.show();
    }



    @FXML
    private void exitApplication() {
        Stage stage = (Stage) taskList.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void deleteTask() {
        int selectedIdx = taskList.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            taskList.getItems().remove(selectedIdx);
        }
    }

    @FXML
    private void markComplete() {
        int selectedIdx = taskList.getSelectionModel().getSelectedIndex();
        if (selectedIdx != -1) {
            String completedTask = taskList.getItems().get(selectedIdx) + " (Completed)";
            taskList.getItems().set(selectedIdx, completedTask);
        }
    }
}
