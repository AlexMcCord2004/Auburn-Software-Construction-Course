package edu.au.cpsc.module6;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BindingsController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}