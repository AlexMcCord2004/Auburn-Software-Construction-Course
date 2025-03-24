package edu.au.cpsc.module2;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SeatReservationApplication extends Application {

    // Instance variable for the reservation
    private SeatReservation seatReservation;

    // Controls we'll need to access throughout
    private TextField flightDesignatorField;
    private DatePicker flightDatePicker;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField numberOfBagsField;
    private CheckBox flyingWithInfantCheckBox;
    private TextField numberOfPassengersField;

    @Override
    public void start(Stage primaryStage) {
        // Instantiate SeatReservation and populate it
        seatReservation = new SeatReservation();
        seatReservation.setFlightDesignator("DL123");
        seatReservation.setFlightDate(java.time.LocalDate.now().plusDays(7));
        seatReservation.setFirstName("Alex");
        seatReservation.setLastName("McCord");
        seatReservation.setNumberOfBags(2);
        seatReservation.makeNotFlyingWithInfant();

        // Initialize UI controls
        flightDesignatorField = new TextField();
        flightDatePicker = new DatePicker();
        firstNameField = new TextField();
        lastNameField = new TextField();
        numberOfBagsField = new TextField();
        flyingWithInfantCheckBox = new CheckBox();
        numberOfPassengersField = new TextField("1");
        numberOfPassengersField.setEditable(false);

        // Link checkbox to number of passengers
        flyingWithInfantCheckBox.setOnAction(e -> {
            if (flyingWithInfantCheckBox.isSelected()) {
                numberOfPassengersField.setText("2");
            } else {
                numberOfPassengersField.setText("1");
            }
        });

        // Labels
        Label flightDesignatorLabel = new Label("Flight Designator:");
        Label flightDateLabel = new Label("Flight Date:");
        Label firstNameLabel = new Label("First Name:");
        Label lastNameLabel = new Label("Last Name:");
        Label numberOfBagsLabel = new Label("Number of Bags:");
        Label flyingWithInfantLabel = new Label("Flying with Infant:");
        Label numberOfPassengersLabel = new Label("Number of Passengers:");

        // Layout: GridPane
        GridPane formGrid = new GridPane();
        formGrid.setPadding(new Insets(20));
        formGrid.setVgap(10);
        formGrid.setHgap(10);

        formGrid.add(flightDesignatorLabel, 0, 0);
        formGrid.add(flightDesignatorField, 1, 0);
        formGrid.add(flightDateLabel, 0, 1);
        formGrid.add(flightDatePicker, 1, 1);
        formGrid.add(firstNameLabel, 0, 2);
        formGrid.add(firstNameField, 1, 2);
        formGrid.add(lastNameLabel, 0, 3);
        formGrid.add(lastNameField, 1, 3);
        formGrid.add(numberOfBagsLabel, 0, 4);
        formGrid.add(numberOfBagsField, 1, 4);
        formGrid.add(flyingWithInfantLabel, 0, 5);
        formGrid.add(flyingWithInfantCheckBox, 1, 5);
        formGrid.add(numberOfPassengersLabel, 0, 6);
        formGrid.add(numberOfPassengersField, 1, 6);

        // Buttons
        Button cancelButton = new Button("Cancel");
        Button saveButton = new Button("Save");

        // Button Actions
        cancelButton.setOnAction(e -> {
            System.out.println("Cancel clicked");
            Platform.exit();
        });

        saveButton.setOnAction(e -> {
            try {
                seatReservation.setFlightDesignator(flightDesignatorField.getText());
                seatReservation.setFlightDate(flightDatePicker.getValue());
                seatReservation.setFirstName(firstNameField.getText());
                seatReservation.setLastName(lastNameField.getText());

                int bags = Integer.parseInt(numberOfBagsField.getText());
                seatReservation.setNumberOfBags(bags);

                if (flyingWithInfantCheckBox.isSelected()) {
                    seatReservation.makeFlyingWithInfant();
                } else {
                    seatReservation.makeNotFlyingWithInfant();
                }

                // Success
                System.out.println(seatReservation);
                Platform.exit();

            } catch (NumberFormatException ex) {
                System.out.println("Error: Please enter a valid number for bags.");
            } catch (IllegalArgumentException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        });


        HBox buttonBox = new HBox(10, cancelButton, saveButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setAlignment(Pos.TOP_RIGHT);

        // Main layout
        BorderPane root = new BorderPane();
        root.setCenter(formGrid);
        root.setBottom(buttonBox);

        // Update the UI with seatReservation values
        updateUI();

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setTitle("Seat Reservation Editor");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateUI() {
        flightDesignatorField.setText(seatReservation.getFlightDesignator());
        flightDatePicker.setValue(seatReservation.getFlightDate());
        firstNameField.setText(seatReservation.getFirstName());
        lastNameField.setText(seatReservation.getLastName());
        numberOfBagsField.setText(String.valueOf(seatReservation.getNumberOfBags()));
        flyingWithInfantCheckBox.setSelected(seatReservation.isFlyingWithInfant());

        // Set passenger count based on checkbox
        numberOfPassengersField.setText(seatReservation.isFlyingWithInfant() ? "2" : "1");
    }

    public static void main(String[] args) {
        launch();
    }
}
