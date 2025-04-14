package edu.au.cpsc.module4;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Objects;

public class ScheduledFlight implements Serializable {

    private static final long serialVersionUID = 1L;

    private String flightDesignator;
    private String departureAirportIdent;
    private LocalTime departureTime;
    private String arrivalAirportIdent;
    private LocalTime arrivalTime;
    private HashSet<DayOfWeek> daysOfWeek;

    public ScheduledFlight() {
        this.daysOfWeek = new HashSet<>();
    }

    // Getters and Setters with Validation

    public String getFlightDesignator() {
        return flightDesignator;
    }

    public void setFlightDesignator(String flightDesignator) {
        if (flightDesignator == null) throw new IllegalArgumentException("Flight designator cannot be null.");
        this.flightDesignator = flightDesignator;
    }

    public String getDepartureAirportIdent() {
        return departureAirportIdent;
    }

    public void setDepartureAirportIdent(String departureAirportIdent) {
        if (departureAirportIdent == null) throw new IllegalArgumentException("Departure airport ident cannot be null.");
        this.departureAirportIdent = departureAirportIdent;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        if (departureTime == null) throw new IllegalArgumentException("Departure time cannot be null.");
        this.departureTime = departureTime;
    }

    public String getArrivalAirportIdent() {
        return arrivalAirportIdent;
    }

    public void setArrivalAirportIdent(String arrivalAirportIdent) {
        if (arrivalAirportIdent == null) throw new IllegalArgumentException("Arrival airport ident cannot be null.");
        this.arrivalAirportIdent = arrivalAirportIdent;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        if (arrivalTime == null) throw new IllegalArgumentException("Arrival time cannot be null.");
        this.arrivalTime = arrivalTime;
    }

    public HashSet<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(HashSet<DayOfWeek> daysOfWeek) {
        if (daysOfWeek == null) throw new IllegalArgumentException("Days of week cannot be null.");
        this.daysOfWeek = daysOfWeek;
    }

    // Optional: add convenience method
    public void addDay(DayOfWeek day) {
        this.daysOfWeek.add(day);
    }

    // toString
    @Override
    public String toString() {
        return flightDesignator + ": " +
                departureAirportIdent + " (" + departureTime + ") → " +
                arrivalAirportIdent + " (" + arrivalTime + ") | Days: " + daysOfWeek;
    }

    // equals and hashCode (based on all fields)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScheduledFlight that)) return false;
        return Objects.equals(flightDesignator, that.flightDesignator) &&
                Objects.equals(departureAirportIdent, that.departureAirportIdent) &&
                Objects.equals(departureTime, that.departureTime) &&
                Objects.equals(arrivalAirportIdent, that.arrivalAirportIdent) &&
                Objects.equals(arrivalTime, that.arrivalTime) &&
                Objects.equals(daysOfWeek, that.daysOfWeek);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightDesignator, departureAirportIdent, departureTime,
                arrivalAirportIdent, arrivalTime, daysOfWeek);
    }
}
