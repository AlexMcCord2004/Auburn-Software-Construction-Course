package edu.au.cpsc.module6;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;
import java.time.LocalTime;

public class FlightUIModel {

    // Properties for flight fields
    private final StringProperty flightDesignator = new SimpleStringProperty();
    private final StringProperty departureAirport = new SimpleStringProperty();
    private final StringProperty arrivalAirport = new SimpleStringProperty();
    private final ObjectProperty<LocalTime> departureTime = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalTime> arrivalTime = new SimpleObjectProperty<>();

    // Model state
    private final BooleanProperty modifiedProperty = new SimpleBooleanProperty(false);
    private final BooleanProperty newProperty = new SimpleBooleanProperty(false);

    // Validation flags
    private final BooleanProperty validDesignatorProperty = new SimpleBooleanProperty(true);
    private final BooleanProperty validDepartureAirportProperty = new SimpleBooleanProperty(true);
    private final BooleanProperty validArrivalAirportProperty = new SimpleBooleanProperty(true);
    private final BooleanProperty validTimeOrderProperty = new SimpleBooleanProperty(true); // dep < arr

    public FlightUIModel() {
        // Track modifications
        flightDesignator.addListener((obs, oldVal, newVal) -> {
            modifiedProperty.set(true);
            validateDesignator(newVal);
        });

        departureAirport.addListener((obs, oldVal, newVal) -> {
            modifiedProperty.set(true);
            validateDepartureAirport(newVal);
        });

        arrivalAirport.addListener((obs, oldVal, newVal) -> {
            modifiedProperty.set(true);
            validateArrivalAirport(newVal);
        });

        departureTime.addListener((obs, oldVal, newVal) -> {
            modifiedProperty.set(true);
            validateTimeOrder();
        });

        arrivalTime.addListener((obs, oldVal, newVal) -> {
            modifiedProperty.set(true);
            validateTimeOrder();
        });
    }

    // Validation logic
    private void validateDesignator(String val) {
        validDesignatorProperty.set(val != null && !val.trim().isEmpty());
    }

    private void validateDepartureAirport(String val) {
        validDepartureAirportProperty.set(val != null && !val.trim().isEmpty());
    }

    private void validateArrivalAirport(String val) {
        validArrivalAirportProperty.set(val != null && !val.trim().isEmpty());
    }

    private void validateTimeOrder() {
        LocalTime dep = departureTime.get();
        LocalTime arr = arrivalTime.get();
        validTimeOrderProperty.set(dep != null && arr != null && dep.isBefore(arr));
    }

    // Properties
    public StringProperty flightDesignatorProperty() { return flightDesignator; }
    public StringProperty departureAirportProperty() { return departureAirport; }
    public StringProperty arrivalAirportProperty() { return arrivalAirport; }
    public ObjectProperty<LocalTime> departureTimeProperty() { return departureTime; }
    public ObjectProperty<LocalTime> arrivalTimeProperty() { return arrivalTime; }

    public BooleanProperty modifiedProperty() { return modifiedProperty; }
    public BooleanProperty newProperty() { return newProperty; }

    public BooleanProperty validDesignatorProperty() { return validDesignatorProperty; }
    public BooleanProperty validDepartureAirportProperty() { return validDepartureAirportProperty; }
    public BooleanProperty validArrivalAirportProperty() { return validArrivalAirportProperty; }
    public BooleanProperty validTimeOrderProperty() { return validTimeOrderProperty; }

    // Convenience methods
    public String getFlightDesignator() { return flightDesignator.get(); }
    public void setFlightDesignator(String val) { flightDesignator.set(val); }

    public String getDepartureAirport() { return departureAirport.get(); }
    public void setDepartureAirport(String val) { departureAirport.set(val); }

    public String getArrivalAirport() { return arrivalAirport.get(); }
    public void setArrivalAirport(String val) { arrivalAirport.set(val); }

    public LocalTime getDepartureTime() { return departureTime.get(); }
    public void setDepartureTime(LocalTime val) { departureTime.set(val); }

    public LocalTime getArrivalTime() { return arrivalTime.get(); }
    public void setArrivalTime(LocalTime val) { arrivalTime.set(val); }

    public boolean isModified() { return modifiedProperty.get(); }
    public void setModified(boolean m) { modifiedProperty.set(m); }

    public boolean isNew() { return newProperty.get(); }
    public void setNew(boolean n) { newProperty.set(n); }
}

