package edu.au.cpsc.part1;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class Part1Controller implements Initializable {

  @FXML
  private TextField messageTextField, echoTextField, firstBidirectionalTextField, secondBidirectionalTextField;

  @FXML
  private ImageView secretOverlayImageView;

  @FXML
  private Slider secretSlider;

  @FXML
  private CheckBox selectMeCheckBox;

  @FXML
  private Label selectMeLabel;

  @FXML
  private TextField tweetTextField;

  @FXML
  private Label numberOfCharactersLabel, validityLabel;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    // 1. messageTextField → echoTextField (one-way)
    echoTextField.textProperty().bind(messageTextField.textProperty());

    // 2. first ↔ second (two-way binding)
    Bindings.bindBidirectional(firstBidirectionalTextField.textProperty(), secondBidirectionalTextField.textProperty());

    // 3. secretSlider → secretOverlayImageView.opacityProperty()
    secretOverlayImageView.opacityProperty().bind(secretSlider.valueProperty());

    // 4. selectMeCheckBox → selectMeLabel
    selectMeLabel.textProperty().bind(selectMeCheckBox.selectedProperty().asString());

    // 5. tweetTextField length → numberOfCharactersLabel
    numberOfCharactersLabel.textProperty().bind(tweetTextField.textProperty().length().asString());

    // 6. tweetTextField length → validityLabel ("Valid"/"Invalid")
    validityLabel.textProperty().bind(
            Bindings.when(tweetTextField.textProperty().length().lessThanOrEqualTo(10))
                    .then("Valid")
                    .otherwise("Invalid")
    );
  }
}
