package edu.au.cpsc.module4;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

public class FlightScheduleController {

    @FXML private FlightTableViewController flightTableViewController;
    @FXML private FlightDetailViewController flightDetailViewController;
    @FXML private Button updateButton;
    @FXML private MenuItem updateMenuItem;

    private ScheduledFlight currentFlight;
    private boolean isNewFlight;

    public void initialize() {
        flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        flightTableViewController.onFlightSelectionChanged(event -> showFlight(event.getSelectedFlight()));
        showFlight(null);
    }

    private void showFlight(ScheduledFlight flight) {
        flightDetailViewController.showFlight(flight);
        currentFlight = (flight == null) ? new ScheduledFlight() : flight;
        isNewFlight = (flight == null);
        String label = isNewFlight ? "Add" : "Update";
        updateButton.setText(label);
        updateMenuItem.setText(label);
    }

    @FXML
    private void updateButtonAction() {
        flightDetailViewController.updateFlight(currentFlight);
        if (isNewFlight) {
            Db.getDatabase().addScheduledFlight(currentFlight);
        } else {
            Db.getDatabase().updateScheduledFlight(currentFlight);
        }
        Db.saveDatabase();
        flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        flightTableViewController.select(currentFlight);
    }

    @FXML
    private void newButtonAction() {
        flightTableViewController.select(null);
    }

    @FXML
    private void deleteButtonAction() {
        if (!isNewFlight) {
            Db.getDatabase().removeScheduledFlight(currentFlight);
            Db.saveDatabase();
            flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        } else {
            flightTableViewController.select(null);
        }
    }

    @FXML private void updateMenuAction() { updateButtonAction(); }
    @FXML private void newMenuAction() { newButtonAction(); }
    @FXML private void deleteMenuAction() { deleteButtonAction(); }
    @FXML private void closeMenuAction() { System.exit(0); }
}