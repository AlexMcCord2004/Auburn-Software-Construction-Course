package edu.au.cpsc.module4;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;

public class FlightDetailViewController {

    @FXML private TextField flightDesignatorField;
    @FXML private TextField departureAirportField;
    @FXML private TextField departureTimeField;
    @FXML private TextField arrivalAirportField;
    @FXML private TextField arrivalTimeField;

    @FXML private ToggleButton monToggle, tueToggle, wedToggle, thuToggle, friToggle, satToggle, sunToggle;

    public void showFlight(ScheduledFlight flight) {
        if (flight == null) {
            flightDesignatorField.clear();
            departureAirportField.clear();
            departureTimeField.clear();
            arrivalAirportField.clear();
            arrivalTimeField.clear();
            setAllDayToggles(false);
            return;
        }
        flightDesignatorField.setText(flight.getFlightDesignator());
        departureAirportField.setText(flight.getDepartureAirportIdent());
        departureTimeField.setText(flight.getDepartureTime().toString());
        arrivalAirportField.setText(flight.getArrivalAirportIdent());
        arrivalTimeField.setText(flight.getArrivalTime().toString());
        setAllDayToggles(false);
        for (DayOfWeek day : flight.getDaysOfWeek()) {
            getToggleForDay(day).setSelected(true);
        }
    }

    public void updateFlight(ScheduledFlight flight) {
        flight.setFlightDesignator(flightDesignatorField.getText());
        flight.setDepartureAirportIdent(departureAirportField.getText());
        flight.setArrivalAirportIdent(arrivalAirportField.getText());
        flight.setDepartureTime(LocalTime.parse(departureTimeField.getText()));
        flight.setArrivalTime(LocalTime.parse(arrivalTimeField.getText()));

        HashSet<DayOfWeek> selectedDays = new HashSet<>();
        if (monToggle.isSelected()) selectedDays.add(DayOfWeek.MONDAY);
        if (tueToggle.isSelected()) selectedDays.add(DayOfWeek.TUESDAY);
        if (wedToggle.isSelected()) selectedDays.add(DayOfWeek.WEDNESDAY);
        if (thuToggle.isSelected()) selectedDays.add(DayOfWeek.THURSDAY);
        if (friToggle.isSelected()) selectedDays.add(DayOfWeek.FRIDAY);
        if (satToggle.isSelected()) selectedDays.add(DayOfWeek.SATURDAY);
        if (sunToggle.isSelected()) selectedDays.add(DayOfWeek.SUNDAY);

        flight.setDaysOfWeek(selectedDays);
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
