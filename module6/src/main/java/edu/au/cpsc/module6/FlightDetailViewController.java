package edu.au.cpsc.module6;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import edu.au.cpsc.module6.FlightUIModel;

import java.time.DayOfWeek;
import java.util.HashSet;

public class FlightDetailViewController {

    @FXML private TextField flightDesignatorField;
    @FXML private TextField departureAirportField;
    @FXML private TextField departureTimeField;
    @FXML private TextField arrivalAirportField;
    @FXML private TextField arrivalTimeField;

    @FXML private ToggleButton monToggle, tueToggle, wedToggle, thuToggle, friToggle, satToggle, sunToggle;

    private final FlightUIModel model = new FlightUIModel();

    public void initialize() {
        // Bind text fields to model
        flightDesignatorField.textProperty().bindBidirectional(model.flightDesignatorProperty());
        departureAirportField.textProperty().bindBidirectional(model.departureAirportProperty());
        arrivalAirportField.textProperty().bindBidirectional(model.arrivalAirportProperty());

        // Parse LocalTime from text field (and vice versa)
        Bindings.bindBidirectional(departureTimeField.textProperty(), model.departureTimeProperty(),
                new javafx.util.StringConverter<>() {
                    public String toString(java.time.LocalTime time) {
                        return time != null ? time.toString() : "";
                    }

                    public java.time.LocalTime fromString(String string) {
                        try {
                            return java.time.LocalTime.parse(string);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                });

        Bindings.bindBidirectional(arrivalTimeField.textProperty(), model.arrivalTimeProperty(),
                new javafx.util.StringConverter<>() {
                    public String toString(java.time.LocalTime time) {
                        return time != null ? time.toString() : "";
                    }

                    public java.time.LocalTime fromString(String string) {
                        try {
                            return java.time.LocalTime.parse(string);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                });

        // Highlight invalid fields
        flightDesignatorField.styleProperty().bind(
                Bindings.when(model.validDesignatorProperty())
                        .then("")
                        .otherwise("-fx-background-color: lightpink"));

        departureAirportField.styleProperty().bind(
                Bindings.when(model.validDepartureAirportProperty())
                        .then("")
                        .otherwise("-fx-background-color: lightpink"));

        arrivalAirportField.styleProperty().bind(
                Bindings.when(model.validArrivalAirportProperty())
                        .then("")
                        .otherwise("-fx-background-color: lightpink"));

        departureTimeField.styleProperty().bind(
                Bindings.when(model.validTimeOrderProperty())
                        .then("")
                        .otherwise("-fx-background-color: lightpink"));

        arrivalTimeField.styleProperty().bind(
                Bindings.when(model.validTimeOrderProperty())
                        .then("")
                        .otherwise("-fx-background-color: lightpink"));
    }

    public FlightUIModel getModel() {
        return model;
    }

    public HashSet<DayOfWeek> getSelectedDays() {
        HashSet<DayOfWeek> selected = new HashSet<>();
        if (monToggle.isSelected()) selected.add(DayOfWeek.MONDAY);
        if (tueToggle.isSelected()) selected.add(DayOfWeek.TUESDAY);
        if (wedToggle.isSelected()) selected.add(DayOfWeek.WEDNESDAY);
        if (thuToggle.isSelected()) selected.add(DayOfWeek.THURSDAY);
        if (friToggle.isSelected()) selected.add(DayOfWeek.FRIDAY);
        if (satToggle.isSelected()) selected.add(DayOfWeek.SATURDAY);
        if (sunToggle.isSelected()) selected.add(DayOfWeek.SUNDAY);
        return selected;
    }

    public void setSelectedDays(HashSet<DayOfWeek> days) {
        setAllDayToggles(false);
        for (DayOfWeek day : days) {
            getToggleForDay(day).setSelected(true);
        }
    }

    private void setAllDayToggles(boolean selected) {
        monToggle.setSelected(selected);
        tueToggle.setSelected(selected);
        wedToggle.setSelected(selected);
        thuToggle.setSelected(selected);
        friToggle.setSelected(selected);
        satToggle.setSelected(selected);
        sunToggle.setSelected(selected);
    }

    private ToggleButton getToggleForDay(DayOfWeek day) {
        return switch (day) {
            case MONDAY -> monToggle;
            case TUESDAY -> tueToggle;
            case WEDNESDAY -> wedToggle;
            case THURSDAY -> thuToggle;
            case FRIDAY -> friToggle;
            case SATURDAY -> satToggle;
            case SUNDAY -> sunToggle;
        };
    }
}
