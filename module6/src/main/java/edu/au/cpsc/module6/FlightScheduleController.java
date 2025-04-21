package edu.au.cpsc.module6;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import edu.au.cpsc.module6.FlightUIModel;

import java.time.DayOfWeek;
import java.util.HashSet;

public class FlightScheduleController {

    @FXML private FlightTableViewController flightTableViewController;
    @FXML private FlightDetailViewController flightDetailViewController;
    @FXML private Button updateButton;
    @FXML private MenuItem updateMenuItem;

    private ScheduledFlight currentFlight;
    private boolean isNewFlight;

    public void initialize() {
        // Set up flight list and selection handler
        flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        flightTableViewController.onFlightSelectionChanged(event -> showFlight(event.getSelectedFlight()));
        showFlight(null);

        // Button enable/disable logic based on model state
        FlightUIModel model = flightDetailViewController.getModel();
        updateButton.disableProperty().bind(
                model.modifiedProperty().not()
                        .or(model.validDesignatorProperty().not())
                        .or(model.validDepartureAirportProperty().not())
                        .or(model.validArrivalAirportProperty().not())
                        .or(model.validTimeOrderProperty().not())
        );
        updateMenuItem.disableProperty().bind(updateButton.disableProperty());
    }

    private void showFlight(ScheduledFlight flight) {
        // Create a new model and populate it
        FlightUIModel model = flightDetailViewController.getModel();

        if (flight == null) {
            model.setFlightDesignator("");
            model.setDepartureAirport("");
            model.setArrivalAirport("");
            model.setDepartureTime(null);
            model.setArrivalTime(null);
            flightDetailViewController.setSelectedDays(new HashSet<>());
            currentFlight = new ScheduledFlight();
            isNewFlight = true;
        } else {
            model.setFlightDesignator(flight.getFlightDesignator());
            model.setDepartureAirport(flight.getDepartureAirportIdent());
            model.setArrivalAirport(flight.getArrivalAirportIdent());
            model.setDepartureTime(flight.getDepartureTime());
            model.setArrivalTime(flight.getArrivalTime());
            flightDetailViewController.setSelectedDays(flight.getDaysOfWeek());
            currentFlight = flight;
            isNewFlight = false;
        }

        model.setModified(false);
        model.setNew(isNewFlight);

        // Update UI button label
        String label = isNewFlight ? "Add" : "Update";
        updateButton.setText(label);
        updateMenuItem.setText(label);
    }

    @FXML
    private void updateButtonAction() {
        // Get model data
        FlightUIModel model = flightDetailViewController.getModel();

        // Update current flight using model
        currentFlight.setFlightDesignator(model.getFlightDesignator());
        currentFlight.setDepartureAirportIdent(model.getDepartureAirport());
        currentFlight.setArrivalAirportIdent(model.getArrivalAirport());
        currentFlight.setDepartureTime(model.getDepartureTime());
        currentFlight.setArrivalTime(model.getArrivalTime());
        currentFlight.setDaysOfWeek(flightDetailViewController.getSelectedDays());

        // Add/update in database
        if (isNewFlight) {
            Db.getDatabase().addScheduledFlight(currentFlight);
        } else {
            Db.getDatabase().updateScheduledFlight(currentFlight);
        }

        Db.saveDatabase();
        flightTableViewController.showFlights(Db.getDatabase().getScheduledFlights());
        flightTableViewController.select(currentFlight);
    }

    @FXML private void newButtonAction() {
        flightTableViewController.select(null);
    }

    @FXML private void deleteButtonAction() {
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
